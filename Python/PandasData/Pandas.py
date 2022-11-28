# Pandas로 데이터 전처리 접근
# 여러 데이터 타입 및 소스, 결측치 또는 오류, 분석 목적에 맞지 않는 변수

# 데이터 읽기
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import seaborn as sns

pd.set_option('display.max_columns', None)
df = pd.read_csv('./data/medical.csv')
print(df.head(20))

# 컬럼명 확인 및 수정
print(df.columns)
df.rename(columns={'Hipertension': 'Hypertension', 'Handcap': 'Handicap'},
          inplace=True)
print(df.columns)

# 데이터 정보 확인
print(df.info())

# 결손 값 확인
print(df.isnull().any(axis=1))
print(df.isnull().any(axis=0))

# 통계량 확인
print(df.describe())

# 이상치 제거
df = df[df.Age >= 0]  # Age가 -1 값인 이상 데이터 제거.
print(df.Age.min())

df = df[(df.Handicap == 0) | (df.Handicap == 1)]
print(df['Handicap'].value_counts())

# 수치형 데이터 변환
df['No-show'] = df['No-show'].map({'Yes': 1, 'No': 0})
print(df['No-show'].value_counts(0))

# Int형 -> DateTime 형으로 변환
df['AppointmentDay'] = pd.to_datetime(df['AppointmentDay'])
df['ScheduledDay'] = pd.to_datetime(df['ScheduledDay'])
df.info()
'''
결과
 3   ScheduledDay    110327 non-null  datetime64[ns, UTC]
 4   AppointmentDay  110327 non-null  datetime64[ns, UTC]
'''

# 새로운 컬럼 추가 (Waiting Day)
# 실제 방문일 - 예약 일 계산
df['waiting_day'] = df['AppointmentDay'].dt.dayofyear - df['ScheduledDay'].dt.dayofyear
print(df.describe())

# 기다린 일 컬럼의 이상치 제거
df = df[df.waiting_day >= 0]
print(df['waiting_day'].min())
print(df.describe())

# Age 컬럼 이상치 확인
print(df.Age.unique())

# Age 컬럼 이상치 제거
df = df[df.Age <= 110]
plt.figure(figsize=(16, 2))
sns.boxplot(x=df.Age)
# plt.show()

# 목적에 적합한 변수 추출
# 목적 1 : 예약 취소율 줄이기
# 목적 2 : 예약 취소 여부와 관련한 변수(컬럼)는 무엇 인지

# 당일 예약과 No-Show의 관계
a = df[df.waiting_day == 0]['waiting_day'].value_counts()
b = df[(df['waiting_day'] == 0) & (df['No-show'] == 1)]['waiting_day'].value_counts()
print(b / a)

no_show = df[df['No-show'] == 1]
show = df[df['No-show'] == 0]

no_show[no_show['waiting_day'] <= 10]['waiting_day'].hist(alpha=0.7, label='no_show')
show[show['waiting_day'] <= 10]['waiting_day'].hist(alpha=0.3, label='show')
plt.legend()
plt.show()

# ScheduleDay / AppointmentDay & No - Show
no_show['AppointmentDay'].hist(alpha=0.7, label='no_show')
show['AppointmentDay'].hist(alpha=0.3, label='show')
plt.legend()
plt.show()

# 재방문 환자와 No - Show
# 재방문 환자 확인
print(df.PatientId.value_counts().iloc[0:10])

# PatientID와 waiting_day
data = df[(df['waiting_day'] >= 50) & (df['No-show'] == 1)].PatientId.value_counts().iloc[0:10]
print(data)

# SMS_received , Waiting_day 와 No-Show
sns.barplot(y='waiting_day', x='SMS_received', hue='No-show', data=df)
plt.show()

tmp = df[['waiting_day', 'SMS_received', 'No-show']].corr()  # 상관 분석
sns.heatmap(tmp, annot=True)
plt.show()

# 성별에 따른 노쇼 여부의 차이
sns.countplot(x='Gender', hue='No-show', data=df)
plt.show()

# No-show의 여성과 남성 비율
F = df[(df['Gender'] == 'F') & (df['No-show'] == 1)]['Gender'].value_counts()
M = df[(df['Gender'] == 'M') & (df['No-show'] == 1)]['Gender'].value_counts()
total_F = df[df['Gender'] == 'F']['Gender'].value_counts()
total_M = df[df['Gender'] == 'M']['Gender'].value_counts()

print(F / total_F)
print(M / total_M)
print("\n")

# 재정지원 여부에 따른 노쇼 발생 비교
sns.countplot(x='Scholarship', hue='No-show', data=df)
plt.show()
SchoFalse = df[(df['Scholarship'] == 0) & (df['No-show'] == 1)]['Scholarship'].value_counts()
SchoTrue = df[(df['Scholarship'] == 1) & (df['No-show'] == 1)]['Scholarship'].value_counts()
total_SchoFalse = df[df['Scholarship'] == 0]['Scholarship'].value_counts()
total_SchoTrue = df[df['Scholarship'] == 1]['Scholarship'].value_counts()

print(SchoFalse / total_SchoFalse)
print(SchoTrue / total_SchoTrue)
print("\n")

# 알콜중독 여부에 따른 노쇼 발생 비교
AlcholFalse = df[(df['Alcoholism'] == 0) & (df['No-show'] == 1)]['Alcoholism'].value_counts()
AlcholTrue = df[(df['Alcoholism'] == 1) & (df['No-show'] == 1)]['Alcoholism'].value_counts()
total_AlchoFalse = df[df['Alcoholism'] == 0]['Alcoholism'].value_counts()
total_AlchoTrue = df[df['Alcoholism'] == 1]['Alcoholism'].value_counts()

print(AlcholFalse / total_AlchoFalse)
print(AlcholTrue / total_AlchoTrue)