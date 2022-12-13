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
df = pd.read_csv('Python/MovieProject/data/movie_aisw.csv')
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

## 3. 영화 개수 확인

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
`movie` 컬럼의 정보를 확인하기 위해 `descrbie()` 함수를 이용해서 정보를 확인했다. 알 수 있는 정보로는 총 개수(4139개)와 중복 값을 제거한 개수(6개), 제일 많이 사용한 데이터(`올빼미`)와 빈도수(2994개)를 알수 있다.
