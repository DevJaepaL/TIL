# 라이브러리 로드
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# 1. 파일 읽어오기
df = pd.read_csv('Python/DataProject/data/measuredData.csv')

# 2. 기본 정보 및 기초 통계량 확인
print(df.describe())

# 3. 한글 컬럼명 영문명으로 변경
print(df.columns) # 컬럼명 확인
# 한글명 컬럼을 영어로 변경
df.rename(columns={'날짜':'Date','아황산가스':'Sulfurous','일산화탄소':'Carbon',
                   '오존':'Ozone','이산화질소':'Nitrogen','기온(°C)':'Temperture(°C)'},inplace=True)
# 출력 확인
print(df.columns)

# 4. 데이터 타입 변경
print(df.dtypes) # 데이터 타입 확인
# Date 컬럼이 `object` 타입 이므로 'datetime64' 데이터로 변환

df['Date'] = pd.to_datetime(df['Date'], 
                            format='%Y-%m-%d %H:%M:%S', 
                            errors='coerce')
print(df['Date'])

# 5. 결측치 확인 및 처리
print(df.isnull().sum()) # 결측치 확인
df = df.dropna(how='any') # NaN 값 모두 삭제후 새로운 데이터프레임 생성
print(df.isnull().sum())

# 6. 상관계수 함수를 이용하여 요소 별 상관관계 분석
corr_df = df.corr() # 상관계수 데이터 프레임 생성
corr_df = corr_df.apply(lambda x: round(x,2)) # 가독성을 위한 소수점 제거
print(corr_df)