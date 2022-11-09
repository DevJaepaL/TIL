## mnist 손글씨 훈련 시각화 (mnistVisualization)

손글씨 인식 학습 및 훈련 데이터를 그래프로 시각화 해봤습니다.
기존의 코드로 짜기에 버거워 자료를 찾고 새롭게 코드를 짜봤습니다.

과정은 다음과 같습니다.

```python
# 필요한 라이브러리 로드
from keras.utils import np_utils
from keras.datasets import mnist
from keras.models import Sequential
from keras.layers import Dense, Activation
import numpy as np
import matplotlib.pyplot as plt
```

필요한 라이브러리들을 로드 합니다.

```python
# 훈련,테스트 데이터 로드
(X_train, Y_train), (X_test, Y_test) = mnist.load_data()
```
그 후 mnist 라이브러리 함수를 이용해 데이터들을 로드합니다.

```pyhon
# 훈련,테스트 세트 분리
X_val = X_train[50000:]
Y_val = Y_train[50000:]
X_train = X_train[:50000]
Y_train = Y_train[:50000]

X_train = X_train.reshape(50000, 784).astype('float32') / 255.0
X_val = X_val.reshape(10000, 784).astype('float32') / 255.0
X_test = X_test.reshape(10000, 784).astype('float32') / 255.0

```
불러온 데이터들을 전처리하기 위해 훈련 , 테스트 데이터를 분리하고
`reshape`로 데이터 가공을 거칩니다.

```python
# 훈련,학습 데이터 선택
train_rand_idxs = np.random.choice(50000, 700)
val_rand_idxs = np.random.choice(10000, 300)
```
이후 1000개의 데이터들을 임의대로 불러와줍니다.

```python
# 데이터 One-hot 인코딩
Y_train = np_utils.to_categorical(Y_train)
Y_val = np_utils.to_categorical(Y_val)
Y_test = np_utils.to_categorical(Y_test)
```
데이터들을 또 다시 `One-Hot 인코딩` 기법으로 가공합니다.

```python
# 모델 세트
model = Sequential()
model.add(Dense(units=5, input_dim=28 * 28, activation='relu'))
model.add(Dense(units=15, activation='relu'))
model.add(Dense(units=10, activation='softmax'))

# 모델 컴파일
model.compile(loss='categorical_crossentropy', optimizer='sgd', metrics=['accuracy'])

# 모델 학습
hist = model.fit(X_train, Y_train, epochs=100, batch_size=10, validation_data=(X_val, Y_val))
```

데이터 전처리를 완료한 후 모델 구성 및 컴파일 후 학습을 실시합니다. 학습 횟수는 총 100번을 거쳤습니다.

```python
# 5. 모델 학습과정 표시하기
fig, loss_ax = plt.subplots()

acc_ax = loss_ax.twinx()

loss_ax.plot(hist.history['loss'], 'y', label='train loss')
loss_ax.plot(hist.history['val_loss'], 'r', label='val loss')

acc_ax.plot(hist.history['accuracy'], 'b', label='train acc')
acc_ax.plot(hist.history['val_accuracy'], 'g', label='val acc')

loss_ax.set_xlabel('epoch')
loss_ax.set_ylabel('loss')
acc_ax.set_ylabel('accuray')

loss_ax.legend(loc='upper left')
acc_ax.legend(loc='lower left')

plt.show()
```

위의 코드를 통해 `matplotlib` 라이브러리를 이용하여 데이터의 시각화를 보여줍니다.

다음은 결과 입니다.

![Alt text](/TensorFlow/mnistVisualization/%ED%95%99%EC%8A%B5%EA%B3%BC%EC%A0%95.PNG)

정상적으로 100번의 학습이 실시 됐습니다.

![Alt text](/TensorFlow/mnistVisualization/%EA%B7%B8%EB%9E%98%ED%94%84%20%EC%8B%9C%EA%B0%81%ED%99%94.png)

손실 , 정확도 값의 그래프 표시입니다.

### 느낀 점

여기서 알 수 있는 점은 훈련(train) 로스 값은 학습을 할 수록 점점 낮아지고 0의 근접하는 값을 얻었지만 테스트(val) 로스 값은 학습을 할 수록 점차 올라감을 알수있었습니다.

정확도 또한 훈련(train)의 정확도 값은 10번 가량의 학습 때 기하급수적으로 올라갔다가 점점 상승세를 보였습니다. 반면 테스트(val) 정확도 값 또한 10번 가량의 학습 때 많이 올라갔다가 학습을 할 수록 일정한 정확도의 값을 보여줬습니다.