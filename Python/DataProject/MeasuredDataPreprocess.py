# 라이브러리 로드
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns
from matplotlib import dates
# 1. 파일 읽어오기
df = pd.read_csv('Python/DataProject/data/measuredData.csv')

# 2. 기본 정보 및 기초 통계량 확인
print(df.describe())

# 3. 한글 컬럼명 영문명으로 변경
print(df.columns) # 컬럼명 확인
# 한글명 컬럼을 영어로 변경
df.rename(columns={'날짜':'Date','아황산가스':'Sulfurous','일산화탄소':'Carbon',
                   '오존':'Ozone','이산화질소':'Nitrogen','기온(°C)':'Temperature(°C)'},inplace=True)
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

# 7. 히스토그램(도수분포표) 시각화
sns.set(style='whitegrid', context='notebook')
sns.pairplot(df.corr(), height=2.0)
plt.show()

# 8. 막대그래프로 일별 현황 그래프 출력

# Date 컬럼의 시간 제거
group_df = df
group_df['Date'] = df['Date'].dt.date

# 년,월,일 그룹화 후, 온도의 평균 새로운 컬럼 추가
group_df['Mean'] = group_df.groupby(['Date'])['Temperature(°C)'].transform("mean")
# 날짜 중복 제거
newGroup = group_df.drop_duplicates(['Date'])
print(newGroup)

# 그래프 시각화
plt.figure(figsize=(14,8))
plt.title('Average Day Temperature')
plt.plot(newGroup['Date'],newGroup['Mean'])
plt.xlabel('Day')
plt.ylabel('Temperature(°C)')
plt.xticks(rotation=45)
plt.grid(True)
ax = plt.gca()
ax.xaxis.set_major_locator(dates.DayLocator()) # 4일 간격의 눈금 1일별로 쪼개기.
plt.show()

# 9. 히트맵으로 상관관계 시각화
plt.figure(figsize=(12,12)) # 그래프 크기 12x12로 지정
sns.heatmap(data = corr_df, annot=True, # data : 상단에서 만든 상관계수, annot = 각 셀의 값 표시
linewidths=3, cmap='YlGn') # cmap = 히트맵 색상
plt.show()

# 10. 산점도 그래프로 온도와 미세먼지(PM10) 확인

tempData = np.array(df['Temperature(°C)'])
pmData = np.array(df['PM10'])

fig = plt.figure(figsize=(8, 8))
ax = fig.add_subplot(111)

# 회귀직선
poly_fit = np.polyfit(tempData, pmData, 1)
poly_1d = np.poly1d(poly_fit)
xs = np.linspace(tempData.min(), pmData.max())
ys = poly_1d(xs)

ax.scatter(tempData, pmData, c='orange', label = 'Corr')
ax.plot(xs,ys, color='black',
        label=f'{poly_fit[1]:.2f}+{poly_fit[0]:.2f}x')
ax.set_xlabel('Temperature')
ax.set_ylabel('PM10')
ax.legend(loc='upper left')
plt.show()

# 11. 미세먼지(PM10)과 초 미세먼지(PM2.5) 관계를 산점도 그래프로 확인
ultraFinePmData = np.array(df['PM2.5'])
pm10Data = np.array(df['PM10'])

fig = plt.figure(figsize=(8,8))
ax = fig.add_subplot(111)

# 회귀직선
poly_fit = np.polyfit(ultraFinePmData, pm10Data, 1)
poly_1d = np.poly1d(poly_fit)
xs = np.linspace(ultraFinePmData.min(), pm10Data.max())
ys = poly_1d(xs)

ax.scatter(ultraFinePmData, pm10Data, c='green', label='PM')
ax.plot(xs,ys, color='black',
        label=f'{poly_fit[1]:.2f}+{poly_fit[0]:.2f}x')
ax.set_xlabel('PM2.5')
ax.set_ylabel('PM10')
ax.legend(loc='upper left')
plt.show()