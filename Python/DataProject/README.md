# 영화별 리뷰 분석

`movie_aisw.csv` 파일 이용하여 다음과 같은 사항을 작성한다.

1. 파일 읽어오기(`movie_aisw.csv`)
2. 데이터 탐색(구성과 데이터 타입 확인)
3. 영화 개수 확인
4. 영화별 평점과 리뷰 수 확인
5. '올빼미' 영화에 대한 형태소 분석(불용어 제거)
6. '올빼미' 영화에 대한 `Wordcloud` 생성
7. 영화 리뷰수가 두번 째로 많은 영화의 분석 및 워드클라우드 생성(1-6번 반복)


## 1. 파일 읽어오기

```python
# 필요 라이브러리 로드
import pandas as pd
import numpy as np
import collections
import matplotlib.pyplot as plt
from wordcloud import WordCloud
from konlpy.tag import Okt

# 1. 파일 읽어오기
df = pd.read_csv('Python\DataProject\data\movie_aisw.csv')
```
해당 사항을 작성하기 위한 라이브러리들을 불러온다.
그 후, 주어진 csv 파일을 `pandas`를 이용해 로드한다.

## 2. 데이터 탐색(구성과 데이터 타입 확인)

```python
# 2. 데이터 탐색(구성과 데이터 타입 확인)
print(df.info())
print(df.dtypes)
```
로드한 데이터의 정보들의 구성과 데이터 타입을 확인하기 위해 `info()` 함수와 `dtypes` 를 사용한다.

### 결과

```python
<class 'pandas.core.frame.DataFrame'>
RangeIndex: 4139 entries, 0 to 4138
Data columns (total 3 columns):
 #   Column    Non-Null Count  Dtype
---  ------    --------------  -----
 0   movie     4139 non-null   object
 1   sentence  4139 non-null   object
 2   score     4139 non-null   int64
dtypes: int64(1), object(2)
memory usage: 97.1+ KB
None
movie       object
sentence    object
score        int64
dtype: object
```

## 3. 영화의 개수 확인

```python
# 3. 영화 개수 확인
print(df['movie'].describe())
print(df['movie'].unique()) # 중복 없이 나온 영화 제목 : 총 6개
```

### 결과
```python
count     4139
unique       6
top        올빼미
freq      2994
Name: movie, dtype: object

['올빼미' '동감' '유포자들' '블랙 팬서: 와칸다 포에버' '스트레인지 월드' '데시벨']
```
`movie` 컬럼의 정보를 확인하기 위해 `descrbie()` 함수를 이용해서 정보를 확인했다. 
알 수 있는 정보로는 총 개수(4139개)와 중복 값을 제거한 수(6개), 제일 많이 사용한 데이터(`올빼미`)와 빈도수(2994개)를 알수 있다.

## 4. 영화 별 평점과 리뷰 수 확인

```python
# 4. 영화별 평점과 리뷰 수 확인
# 데이터 프레임의 GroupBy 함수를 이용해 각 행에 영화의 이름별로 그룹화 하여
# 리뷰 및 평점을 영화 별 펑점과 리뷰 총 개수 출력
movieGroupBy = df.groupby('movie')
print(movieGroupBy.count())
```

### 결과
```python                    
    movie                    sentence  score
    데시벨                        340    340
    동감                          217    217
    블랙 팬서: 와칸다 포에버         383    383
    스트레인지 월드                 179    179
    올빼미                        2994   2994
    유포자들                        26     26
```
`Pandas`의 `groupby`를 이용하여 `movie` 컬럼의 중복 데이터들을 그룹화 하여 평점 및 리뷰의 총 개수를 확인했다.

## 5. '올빼미' 영화에 대한 형태소 분석(불용어 제거)

```python
# 5. '올빼미' 영화에 대한 형태소 분석(불용어 제거)
#  원본 데이터프레임에서 'movie' 컬럼의 '올빼미'가 포함된 행만 추출하여 
#  새로운 데이터 프레임인 `df_owl` 생성.
df_owl = df[df['movie'].str.contains('올빼미')].copy()
print(df_owl.count())
```
이제 **올빼미** 영화의 데이터만 추출하여 형태소를 분석 해야한다.
나 같은 경우엔 `contains`를 이용해, 올빼미인 값만 추출하여 `df_owl` 이라는 새로운 데이터 프레임을 생성했다. 그 후 **올빼미** 데이터의 총 개수를 확인했다.

```python
movie       2994
sentence    2994
score       2994
dtype: int64
```
총 2994개의 데이터가 존재한 것을 확인할 수 있다.

그 후 중복 값 및 결측치가 존재한 지 확인을 했다.
```python
# 중복 제거 및 결측치 확인
print(df_owl['movie'].nunique())
print(df_owl.isnull().sum()) # 결측치와 중복값 없는것을 확인
```
**결과**

```python
1
movie       0
sentence    0
score       0
dtype: int64
```
그 다음 작업으로 불용어를 만든 뒤, 형태소 토큰화 작업을 하여 빈도수가 많은 상위 50개의 단어를 생성해줬다.

```python
# 불용어 제거 및 형태소 토큰화
okt = Okt()
stopword = ['점', '정말', '왜', '말', '그', '없다', 
            '정도', '걸', '뭐', '이건','영화', '완전', 
            '좀', '있는', '거', '나','이', '볼', '입니다', 
            '것', '이런', '더', '수', '때']
list = []
for sentence in df_owl['sentence']:
    s_list = okt.pos(sentence)
    for word, tag in s_list:
        if word not in stopword:
            if tag in ['Noun', 'Adjective']:
                list.append((word))

counts = collections.Counter(list)
tag = counts.most_common(50)
# 빈도수가 많은 상위 50개의 단어 출력
print(tag)
```
### 결과

```python
[('연기', 1089), ('배우', 695), ('진짜', 486), ('유해진', 423), ('최고', 387), ('스토리', 377), ('연출', 370), ('류준열', 361), ('몰입', 294), ('재밌게', 222), ('긴장감', 221), ('연기력', 210), ('만', 201), ('시간', 195), ('올해', 191), ('반전', 157), ('역
사', 156), ('꼭', 149), ('생각', 147), ('감', 139), ('보고', 126), ('사극', 125), ('중', 122), ('간만', 117), ('류준', 116), ('장면', 111), ('추천', 108), ('기대', 107), ('왕', 102), ('몰입도', 100), ('재밌어요', 97), ('올빼미', 96), ('소름', 94), ('스릴러', 92), ('대박', 92), ('처음', 87), ('끝', 87), ('안', 83), ('중간', 82) ...
```
## 6. '올빼미' 영화에 대한 WordCloud 생성

```python
# 6. '올빼미' 영화에 대한 WordCloud 생성
font_path = 'C:\Windows\Fonts\malgun.ttf'
wc = WordCloud(font_path=font_path, background_color='black', max_font_size=50)

# 빈도수가 많은 상위 50개의 단어로 워드클라우드 생성
cloud = wc.generate_from_frequencies(dict(tag))
# 화면에 출력
plt.imshow(cloud)
plt.show()
```
마지막으로 **상위 50개의 빈도수가 높은 단어들로 워드클라우드를 생성**했다. 

### 결과

![](./ResultIMG/Figure_1.png)

## 7. 영화 리뷰수가 두번째로 많은 영화의 분석 및 워드 클라우드 생성 (1-6번 과정 반복)

```python
# 필요 라이브러리 로드
import pandas as pd
import numpy as np
import collections
import matplotlib.pyplot as plt
from wordcloud import WordCloud
from konlpy.tag import Okt

# 7. 영화 리뷰수가 두번째로 많은 영화의 분석 및
#    워드 클라우드 생성 (1-6번 과정 반복)

# 1. 파일 읽어오기
df = pd.read_csv('Python/MovieProject/data/movie_aisw.csv')

# 2. 데이터 탐색(구성과 데이터 타입 확인)
print(df.info())
print(df.dtypes)

# 3. 영화 개수 확인
print(df['movie'].describe())
print(df['movie'].unique()) # 중복 없이 나온 영화 제목 : 총 6개

# 4. 영화별 평점과 리뷰 수 확인
#    영화 이름별로 그룹화 후, 리뷰 수 내림차순으로 정렬
df_groupby = df.groupby('movie').count()
df_groupby_sort = df_groupby.sort_values(by='sentence', ascending=False)
print(df_groupby_sort)

# 5. '블랙 팬서: 와칸다 포에버' 영화대한 형태소 분석(불용어 제거)
df_2 = df[df['movie'].str.contains\
                     ('블랙 팬서: 와칸다 포에버')].copy()
print(df_2.count())

# 중복 제거 및 결측치 확인
print(df_2['movie'].nunique())
print(df_2.isnull().sum()) # 결측치와 중복값 없음.

# 불용어 제거 및 형태소 토큰화
okt = Okt()

# 불용어의 배열 집합
stopword = ['점', '정말', '왜', '말', '그', '없다', 
            '정도', '걸', '뭐', '이건','영화', '완전', 
            '좀', '있는', '거', '나','이', '볼', '입니다', 
            '것', '이런', '더', '수', '때']
list = []
for sentence in df_2['sentence']:
    s_list = okt.pos(sentence)
    for word, tag in s_list:
        if word not in stopword:
            if tag in ['Noun', 'Adjective']:
                list.append((word))

counts = collections.Counter(list)
tag = counts.most_common(50)
# 빈도수가 많은 상위 50개의 단어 출력
print(tag)

# 6. '블랙 팬서: 와칸다 포에버' 영화에 대한 WordCloud 생성
font_path = 'C:/Windows/Fonts/malgun.ttf'
wc = WordCloud(font_path=font_path, background_color='black', max_font_size=50)

# 빈도수가 많은 상위 50개의 단어로 워드클라우드 생성
cloud = wc.generate_from_frequencies(dict(tag))

# 화면에 출력
plt.imshow(cloud)
plt.show()
```

### 2 번째로 많은 데이터를 찾은 과정

```python
df_groupby = df.groupby('movie').count()
df_groupby_sort = df_groupby.sort_values(by='sentence', ascending=False)
print(df_groupby_sort)
```

1-3 번 과정은 기존 영화 분석 코드와 같지만 4번 과정에서 **리뷰수가 2번 째로 많은 데이터를 확인**하기 위해 `GroupBy`로 데이터들을 묶어준 후 `sort_values`를 이용해 값들을 정렬해줬다.

```python
movie                    sentence  score
올빼미                       2994   2994
블랙 팬서: 와칸다 포에버        383    383 <<
데시벨                       340    340
동감                         217    217
스트레인지 월드               179    179
유포자들                     26     26
```

여기서 **블랙 팬서: 와칸다 포에버** 가 두 번째로 많은 데이터임을 알 수 있었다.
나머지 과정은 기존 코드와 같다.

## 결과

![](./ResultIMG/Figure_1.png)

# 환경 데이터 분석