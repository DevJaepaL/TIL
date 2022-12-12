# 필요 라이브러리 로드
import pandas as pd
import matplotlib.pyplot as plt
from wordcloud import wordcloud
from konlpy.tag import Okt

# 1. 파일 읽어오기
df = pd.read_table('Python\MovieProject\data\movie_aisw.csv')
print(df)
df.info()
print(df.dtypes)