# Tensorflow and tf.Keras 로드
import tensorflow as tf
# 필요 라이브러리 로드
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
# 트레이닝(훈련) 데이터 세트 확인
print("====================================")
print("트레이닝 훈련 데이터 세트 확인")
print(train_images.shape)  # 60,000 개의 데이터와 28 * 28 사이즈의 3차원 배열
print(len(train_labels))  # 트레이닝 정답 레이블 개수 60,000 개
print(train_labels)
print("====================================")
# 테스트(시험) 데이터 세트 확인
print("테스트 데이터 세트 확인")
print(test_images.shape)
print(len(test_labels))
print(test_labels)

# ======================================================= #
# 이미지 전처리 후 확인 및 출력
train_images = train_images / 255.0
test_images = test_images / 255.0
plt.figure()
plt.imshow(test_images[0])  # 첫 번째 이미지 보여주기.
plt.colorbar()
plt.grid(False)
plt.show()

# ======================================================= #
# 훈련 세트에서 처음 이미지(0 ~ 25개)와 그 아래 클래스 이름 출력
plt.figure(figsize=(10, 10))
for i in range(25):
    plt.subplot(5, 5, i + 1)
    plt.xticks([])
    plt.yticks([])
    plt.grid(False)
    plt.imshow(train_images[i], cmap=plt.cm.binary)  # 이미지 출력
    plt.xlabel(class_names[train_labels[i]])  # 이미지 아래 옷의 클래스(이름) 출력
plt.show()

# ======================================================= #
# 학습 모델의 선정
model = tf.keras.Sequential([
    tf.keras.layers.Flatten(input_shape=(28, 28)),  # 입력 이미지 = 28*28 사이즈의 2차원 배열
    tf.keras.layers.Dense(1024, activation='relu'),  # 입력층 뉴런 수 1024 개
    tf.keras.layers.Dense(10, activation='softmax')  # 출력층 뉴런 수 10 개
])

# ======================================================= #
# 모델 컴파일 및 실행 #
model.compile(
    optimizer='adam',
    loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
    metrics=['accuracy']
)
# optimizer : 모델이 인식하는 데이터와 해당 손실 함수 기반으로 모델이 업데이트 되는 방식
# loss : 훈련 중 모델이 얼마나 정확한 지 측정한다. 이 함수는 최소화 해야함.
# metrics : 훈련 및 테스트 단계를 모니터링 하는 데 사용한다.

model.summary()  # 구현한 모델 확인
model.fit(train_images, train_labels, epochs=3)
#  train_images = 입력 데이터 , train_labels = 가중치 , epochs = 학습 횟수

'''
10번 학습 결과.
Epoch 10/10
1875/1875 [==============================] - 5s 2ms/step - loss: 0.2914 - accuracy: 0.8931
accuracy(정확도)가 의미하는 바는 트레이닝을 할 때마다 train_images가 레이블(정답) 값에 얼마나 가까운 값인지 의미한다.
즉 1.00 의 값에 가까울 수록 정확도가 높다.
'''

# ======================================================= #
# 모델 평가 #
test_loss, test_acc = model.evaluate(test_images, test_labels, verbose=2)
print('\n Test accuracy : ', test_acc)
# Test accuracy :  0.8715000152587891
# ======================================================= #
# 데이터 모델 예측 #
predictions = model.predict(test_images)
print(predictions[0])

''' 예측 결과
[4.0496203e-13 2.2027166e-15 3.3040091e-11 1.1003226e-15 2.0756311e-13
 2.6347626e-08 1.1427883e-13 1.2252820e-04 5.3477293e-14 9.9987745e-01]
 제일 높은 예측 값 : 9.9987745e-01
 Ankle boot의 모델 예측이 가장 높다.
'''
# ======================================================= #
# 이미지 예측 확인 및 그래프 출력 함수
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
        100*np.max(predictions_array),
        class_names[true_label]),
        color=color)


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
# 함수 추가 후 모델 이미지가 예측한 값과 일치한지 확인.
i = 12
plt.figure(figsize=(6, 3))  # 그래프 출력
plt.subplot(1, 2, 1)
plot_image(i, predictions[i], test_labels, test_images)
plt.subplot(1, 2, 2)
plot_value_array(i, predictions[i], test_labels)
plt.show()

# ======================================================= #
# 몇 개의 이미지 예측 출력
num_rows = 5
num_cols = 3
num_images = num_rows*num_cols
plt.figure(figsize=(2*2*num_cols, 2*num_rows))
for i in range(num_images):
    plt.subplot(num_rows, 2*num_cols, 2*i+1)
    plot_image(i, predictions[i], test_labels, test_images)
    plt.subplot(num_rows, 2*num_cols, 2*i+2)
    plot_value_array(i, predictions[i], test_labels)
    plt.tight_layout()
    plt.show()
# ======================================================= #