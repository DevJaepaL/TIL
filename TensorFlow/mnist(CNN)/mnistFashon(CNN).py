# 라이브러리 로드
import tensorflow as tf
from tensorflow import keras
import numpy as np
import matplotlib.pyplot as plt

# ======================================================= #
# 패션 MNIST 데이터 세트 로드
fashion_mnist = tf.keras.datasets.fashion_mnist
(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()
# 이미지 = 28 * 28 numpy 배열 , 픽셀값은 0 ~ 255
# 레이블 = 0 ~ 9 까지의 정수값의 배열 이며 옷의 클래스를 의미한다.
class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']
# ======================================================= #
# 이미지 데이터 전처리
train_images = train_images / 255.0
test_images = test_images / 255.0
# ======================================================= #
# CNN 모델 생성 ( 11-03 )
model = keras.Sequential()
model.add(keras.layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
model.add(keras.layers.MaxPooling2D((2, 2)))
model.add(keras.layers.Conv2D(64, (3, 3), activation='relu'))
model.add(keras.layers.MaxPooling2D((2, 2)))
model.add(keras.layers.Conv2D(64, (3, 3), activation='relu'))
# 분류 레이어 추가
model.add(keras.layers.Flatten())
model.add(keras.layers.Dense(64, activation='relu'))
model.add(keras.layers.Dense(10, activation='softmax'))

# 구현 모델 확인
model.summary()
# ======================================================= #
# 모델 컴파일
model.compile(optimizer='adam',
              loss='sparse_categorical_crossentropy',
              metrics=['accuracy'])
# optimizer :   모델이 인식하는 데이터와 해당 손실 함수 기반으로 모델이 업데이트 되는 방식
# loss :        훈련 중 모델이 얼마나 정확한 지 측정한다. 이 함수는 최소화 해야함.
# metrics :     훈련 및 테스트 단계를 모니터링 하는 데 사용한다.

# 모델 학습
model.fit(train_images, train_labels, epochs=10)
# ======================================================= #
# 모델 평가
test_loss, test_acc = model.evaluate(test_images, test_labels, verbose=2)
print('\n Test accuracy : ', test_acc)
# ======================================================= #
# 데이터 모델 예측
predictions = model.predict(test_images)
print(predictions[0])

predictSum = 0
for pred in predictions[0]:
    predictSum += pred
print("예측 값 : ", predictSum)

# ======================================================= #
# 이미지 출력 함수
def plot_image(i, predictions_array, true_label, img):
    true_label, img = true_label[i], img[i]
    plt.grid(False)
    plt.xticks([])
    plt.yticks([])
    plt.imshow(img, cmap=plt.cm.binary)

    predicted_label = np.argmax(predictions_array)
    if predicted_label == true_label:
        color = 'blue'
    else:
        color = 'red'

    plt.xlabel("{} {:2.0f}% ({})".format(class_names[predicted_label],
                                         100 * np.max(predictions_array),
                                         class_names[true_label]),
                                         color = color)

# 그래프 출력 함수
def plot_value_array(i, predictions_array, true_label):
    true_label = true_label[i]
    plt.grid(False)
    plt.xticks(range(10))
    plt.yticks([])
    thisplot = plt.bar(range(10), predictions_array, color="#777777")
    plt.ylim([0, 1])
    predicted_label = np.argmax(predictions_array)

    thisplot[predicted_label].set_color('red')
    thisplot[true_label].set_color('blue')


# ======================================================= #
# 첫 번째 테스트 이미지 출력
i = 12
plt.figure(figsize=(6, 3))  # 그래프 출력
plt.subplot(1, 2, 1)
plot_image(i, predictions[i], test_labels, test_images)
plt.subplot(1, 2, 2)
plot_value_array(i, predictions[i], test_labels)
plt.show()
# ======================================================= #
# 전체 의류 이미지 예측 결과 화면 출력
num_rows = 5
num_cols = 3
num_images = num_rows * num_cols
plt.figure(figsize=(2 * 2 * num_cols, 2 * num_rows))
for i in range(num_images):
    plt.subplot(num_rows, 2 * num_cols, 2 * i + 1)
    plot_image(i, predictions[i], test_labels, test_images)
    plt.subplot(num_rows, 2 * num_cols, 2 * i + 2)
    plot_value_array(i, predictions[i], test_labels)
plt.tight_layout()
plt.show()
# ======================================================= #
