# 필요 라이브러리 로드
import pandas as pd
import numpy as np
import collections
import matplotlib.pyplot as plt
from wordcloud import WordCloud
from konlpy.tag import Okt

# 1. 파일 읽어오기
df = pd.read_csv('Python/DataProject/data/movie_aisw.csv')

# 2. 데이터 탐색(구성과 데이터 타입 확인)
print(df.info())
print(df.dtypes)

# 3. 영화 개수 확인
print(df['movie'].describe())
print(df['movie'].unique()) # 중복 없이 나온 영화 제목 : 총 6개

# 4. 영화별 평점과 리뷰 수 확인
# 데이터 프레임의 GroupBy 함수를 이용해 각 행에 영화의 이름별로 그룹화 하여
# 리뷰 및 평점을 영화 별 펑점과 리뷰 총 개수 출력
movieGroupBy = df.groupby('movie')
print(movieGroupBy.count())

# 5. '올빼미' 영화에 대한 형태소 분석(불용어 제거)
#  원본 데이터프레임에서 'movie' 컬럼의 '올빼미'가 포함된 행만 추출하여 
#  새로운 데이터 프레임인 `df_owl` 생성.
df_owl = df[df['movie'].str.contains('올빼미')].copy()
print(df_owl.count())
# 원본 데이터 수(df) : 4139
# 올빼미 영화의 데이터(df_owl) : 2994

# 중복 제거 및 결측치 확인
print(df_owl['movie'].nunique())
print(df_owl.isnull().sum()) # 결측치와 중복값 없는것을 확인

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

# # 6. '올빼미' 영화에 대한 WordCloud 생성
font_path = 'C:\Windows\Fonts\malgun.ttf'
wc = WordCloud(font_path=font_path, background_color='black', max_font_size=50)

# # 빈도수가 많은 상위 50개의 단어로 워드클라우드 생성
cloud = wc.generate_from_frequencies(dict(tag))
# # 화면에 출력
plt.imshow(cloud)
plt.show()
