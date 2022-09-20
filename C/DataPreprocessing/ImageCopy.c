#include <Windows.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#define MIN(x,y) x < y ? x : y
#define MAX(x,y) x > y ? x : y
#pragma warning(disable:4996)
HWND hwnd;
HDC	 hdc;

enum FORMAT { EMPTY, GREY, RGB, YCBCR, YCBCR420, BLOCK };

typedef struct {
	unsigned rows;	// Image height
	unsigned cols;	// Image width
	char format;	// Image format
	unsigned long total;	// Image Total Bytes
	unsigned levels;	// Color Level
	unsigned char* content;
} ImageType;

typedef ImageType* Image;

Image imageAllocate(unsigned rows, unsigned cols, char format, unsigned levels) {
	Image im = malloc(sizeof(ImageType));
	im->rows = rows;
	im->cols = cols;
	im->format = format;
	im->levels = levels;
	im->content = NULL;

	if (im->format == EMPTY)
		return im;

	switch (im->format) {
	case GREY:
		im->total = im->cols * im->rows;
		break;
	case RGB:
		im->total = 3 * im->cols * im->rows;
		break;
	case YCBCR:
		im->total = 3 * im->cols * im->rows;
		break;
	case YCBCR420:
		im->total = 3 * im->cols * im->rows / 2;
		break;
	default:
		break;
	}

	im->content = malloc(im->total * sizeof(short));
	return im;
}

Image readPBMImage(const char* filename) {
	FILE* pgmFile;
	int k;

	char signature[3];
	unsigned cols, rows, levels;

	Image im = imageAllocate(0, 0, EMPTY, 0);

	pgmFile = fopen(filename, "rb");

	if (pgmFile == NULL) {
		perror("Cannot open file to read !");
		fclose(pgmFile);
		return imageAllocate(0, 0, EMPTY, 0);
	}

	fgets(signature, sizeof(signature), pgmFile);
	if (strcmp(signature, "P5") != 0 && strcmp(signature, "P6") != 0) {
		perror("Wrong file Type !");
		fclose(pgmFile);
		return im;
	}

	// read Header File
	fscanf(pgmFile, "%d %d %d", &cols, &rows, &levels);
	fgetc(pgmFile);

	if (strcmp(signature, "P5") == 0) {
		im = imageAllocate(rows, cols, GREY, levels);
		for (k = 0; k < im->total; ++k)
			im->content[k] = (char)fgetc(pgmFile);
	}

	else if (strcmp(signature, "P6") == 0) {
		im = imageAllocate(rows, cols, RGB, levels);
		unsigned long gOffset = im->cols * im->rows;
		unsigned long bOffset = 2 * im->cols * im->rows;
		for (k = 0; k < im->total / 3; ++k) {
			im->content[k] = (unsigned char)fgetc(pgmFile);
			im->content[k + gOffset] = (unsigned char)fgetc(pgmFile);
			im->content[k + bOffset] = (unsigned char)fgetc(pgmFile);
		}
	}

	fclose(pgmFile);
	return im;
}

void writeImage(const char* filename, const Image im) {
	FILE* pgmFile;
	int k = 0;

	pgmFile = fopen(filename, "wb");
	if (pgmFile == NULL) {
		perror("Cannot open file to write");
		exit(EXIT_FAILURE);
	}

	if (im->format == GREY)
		fprintf(pgmFile, "%s", "P5 ");
	else if (im->format == RGB)
		fprintf(pgmFile, "%s", "P6 ");
	else {
		perror("Unknown file format\n");
		fclose(pgmFile);
		return;
	}

	fprintf(pgmFile, "%d %d ", im->cols, im->rows);
	fprintf(pgmFile, "%d ", im->levels);

	/* 이미지 수정 */
	if (im->format == GREY) {
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)255, pgmFile);
				k++;
			}
		}
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)im->content[k], pgmFile);
				k++;
			}
		}
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)0, pgmFile);
				k++;
			}
		}
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)im->content[k], pgmFile);
				k++;
			}
		}
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)255, pgmFile);
				k++;
			}
		}
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)im->content[k], pgmFile);
				k++;
			}
		}
		for (int i = 0; i < im->rows / 7; i++) {
			for (int j = 0; j < im->cols; j++) {
				fputc((char)0, pgmFile);
				k++;
			}
		}
		while (k < im->total) {
			fputc((char)0, pgmFile);
			k++;
		}
	}

	else if (im->format == RGB) {
		unsigned long gOffset = im->cols * im->rows;
		unsigned long bOffset = 2 * im->cols * im->rows;
		for (k = 0; k < im->total / 3; ++k) {
			fputc((unsigned char)im->content[k], pgmFile);
			fputc((unsigned char)im->content[k + gOffset], pgmFile);
			fputc((unsigned char)im->content[k + bOffset], pgmFile);
		}
	}

	fclose(pgmFile);
}

void equalize(Image im) {
	int k;

	int minVal[3] = { 255,255,255 },
		maxVal[3] = { 0,0,0 };

	float normCoef[3];	// normalization coefficietns

	if(im->format == GREY)
		for (k = 0; k < im->total; k++) {
			minVal[0] = MIN(im->content[k], minVal[0]);
			maxVal[0] = MAX(im->content[k], maxVal[0]);
		}

	if (im->format == GREY) {	// grayscale image
		normCoef[0] = (float)im -> levels / (maxVal[0] - minVal[0]);
		for (k = 0; k < im->rows * im->cols; k++)
			im->content[k] = (im->content[k] - minVal[0]) * normCoef[0];
	}
}

void DrawImage(Image im) {

	hwnd = GetForegroundWindow();
	hdc = GetWindowDC(hwnd);
	for (int y = 0; y < im->rows; ++y) {
		for (int x = 0; x < im->cols; x++) {
			SetPixel(hdc, 250 + x, 250 + y, RGB(im->content[y * im->cols + x],
												im->content[y * im->cols + x],
												im->content[y * im->cols + x]));
		}
	}
}

void imageRelease(Image im) {
	im->format = EMPTY;
	if (im->content != NULL)
		free(im->content);
	im->content = NULL;
	free(im);
}

void DrawHistogram(Image im)
{
	/*************************

		이미지 픽셀 값 초기화 
	
	**************************/
	short* horizontal = malloc(sizeof(short) * im->cols);
	short* vertical = malloc(sizeof(short) * im->rows);

	for (int i = 0; i < im->cols; i++)
		horizontal[i] = 0;

	for (int i = 0; i < im->rows; i++)
		vertical[i] = 0;


	/*	출력 시 나오는 녹색 히스토그램이 의미하는 바는
		흑색에 근접한 픽셀의 분포에 따라 히스토그램의
		높낮이가 다르다.*/
	for (int y = 0; y < im->rows; ++y)
	{
		for (int x = 0; x < im->cols; x++) {
			if (im->content[y * im->cols + x] == 0) {
				horizontal[x]++;
			}
		}
	}

	for (int x = 0; x < im->cols; ++x)
	{
		for (int y = 0; y < im->rows; y++) 
		{
			if (im->content[y * im->cols + x] == 0)
			{
				vertical[y]++;
			}
		}
	}

	hwnd = GetForegroundWindow();
	hdc = GetWindowDC(hwnd);

	for (int i = 0; i < im->cols; i++)
	{
		for (int j = 0; j < horizontal[i] * 20; j++)
		{
			SetPixel(hdc, 250 + i, 250 - j, RGB(0, 255, 0));
		}
	}

	for (int i = 0; i < im->rows; i++) 
	{
		for (int j = 0; j < vertical[i] * 20; j++)
		{
			SetPixel(hdc, 250 - j, 250 + i, RGB(0, 255, 0));
		}
	}

	free(horizontal);
	free(vertical);
}

int main(void) {
	Image img,copyImg = 0;
	
	system("cls");
	char imgPath[] = "frog.pbm";
	img = readPBMImage(imgPath);

	// Copy & Edit PBM Image.
	writeImage("frogCopy.pbm", img);
	char copyFileName[] = "frogCopy.pbm";
	copyImg = readPBMImage(copyFileName);
	DrawImage(copyImg);
	imageRelease(copyImg);
	
	return 0;
}
