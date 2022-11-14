# 데이터 증강 & 드롭아웃 모델 적용

<a href="">소스 코드(GitHub)</a>

CNN 모델을 통해 꽃 이미지 분류를 한다.
CNN 모델 개선을 두 가지의 방법을 이용하여 결과 비교 분석을 한다. 

다음과 같다.

## 1. DropOut만 적용 했을 때의 결과

DropOut만 적용 했을 때의 코드이다.

```python
# 모델 구성 및 컴파일
# 과대적합 방지를 위한 모델 개선
num_classes = 5
model = tf.keras.Sequential([
    tf.keras.layers.experimental.preprocessing.Rescaling(1. / 255,
                                            input_shape=(img_height, img_width, 3)),
    tf.keras.layers.Conv2D(16, 3, padding='same', activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Conv2D(32, 3, padding='same', activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Conv2D(64, 3, padding='same', activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Dropout(0.2),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(num_classes)
])

model.compile(optimizer='adam',
              loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
              metrics=['accuracy'])

model.summary()
```

모델 적용 후 학습결과의 대한 데이터 시각화이다.

![드롭아웃](./과제%20이미지/드롭아웃%20적용%20데이터%20시각화.png)

![드롭아웃 텍스트](./과제%20이미지/드롭아웃%20적용%20모델%20텍스트.PNG)

**결과 분석**

+ **학습(Training)에 대한 정확도**와 로스 값은 대략 **20회의 학습**에서 기하급수적으로 정확도에 근접, 로스값이 적음을 알수 있다.
+ **훈련(Validation)에 대한 정확도**와 로스 값은 학습 횟수가 늘어날수록 정확도는 `5-60%` 이며
로스 값은 계속 늘어나며 `60%`에 근접함을 알 수 있다.

## 2. 데이터 증강만 적용 했을 때의 결과

DropOut을 제외하고 데이터 증강만 모델을 추가한 코드이다.

```python
# 데이터 증강
data_augmentation = keras.Sequential(
    [
        tf.keras.layers.experimental.preprocessing.RandomFlip("horizontal",
                                                              input_shape=(img_height,
                                                                           img_width,
                                                                           3)),
        tf.keras.layers.experimental.preprocessing.RandomRotation(0.1),
        tf.keras.layers.experimental.preprocessing.RandomZoom(0.1)
    ]
)

# 모델 구성 및 컴파일
model = tf.keras.Sequential([
    data_augmentation,
     tf.keras.layers.experimental.preprocessing.Rescaling(1. / 255,
                                            input_shape=(img_height, img_width, 3)),
    tf.keras.layers.Conv2D(16, 3, padding='same', activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Conv2D(32, 3, padding='same', activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Conv2D(64, 3, padding='same', activation='relu'),
    tf.keras.layers.MaxPooling2D(),
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(num_classes)
])
```

데이터증강 추가 후 모델 학습 및 훈련 데이터 시각화 결과이다.

![데이터 증강](과제%20이미지/드롭아웃해제+데이터증강%20시각화.png)

![데이터 증강 텍스트](과제%20이미지/드롭아웃%20해제%20+%20데이터증강만%20적용한%20모델.PNG)

결과 분석

+ 드롭아웃만 적용했을 때와 달리 정확도와 로스가 점차 증가 및 감소하다가 
**40회 가량의 학습에서 상당히 증가,감소** 함을 알 수 있다.

+ 훈련(Validation)에서는 대략 정확도가 학습 횟수가 적음에도 불구하고 `60%`의 정확도를 보여줬다.
그러나 학습 횟수가 20번에 넘어가며 정확도가 `70%`에서 머물고 있다는 것을 알 수 있다.

+ 훈련에 대한 손실 값은 **학습횟수가 늘수록 손실 값이 매우 커짐**을 알 수 있다.