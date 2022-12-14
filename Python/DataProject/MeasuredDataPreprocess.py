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

