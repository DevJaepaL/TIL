import matplotlib.pyplot as plt
import pandas as pd
import numpy as np
# 사이킷런을 통해 평균 제곱 오차 계산이 가능하다.
from sklearn.metrics import mean_squared_error as mse
from sklearn import linear_model

# 오염도에 따른 측정 결과 데이터
data_home = 'https://github.com/dknife/ML/raw/main/data/'
lin_data = pd.read_csv(data_home + 'pollution.csv')

x = lin_data['input'].to_numpy()
y = lin_data['pollution'].to_numpy()
x = x[:, np.newaxis]

regr = linear_model.LinearRegression()
regr.fit(x,y) # 데이터를 기반으로 최적의 선형 회귀 모델 생성

lin_data.plot(kind='scatter', x='input', y='pollution')
y_pred = regr.predict(([0], [1]))
plt.plot([0,1], y_pred, color='red')
plt.show()
