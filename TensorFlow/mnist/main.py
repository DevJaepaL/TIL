# 모듈 로드
import os
from PIL import Image
import numpy as np
import tensorflow as tf
from tensorflow import keras
from keras import layers
import matplotlib.pyplot as plt
import random

# 데이터 수집을 위한 배열 선언
all_files = []
x_train_datas=[]
y_train_datas=[]

# 폴더 안에 있는 이미지 로드 후 오름차순 정리
for i in range(0,10):
    path_dir = './images/training/{0}'.format(i)
    file_list = os.listdir(path_dir)
    file_list.sort()
    all_files.append(file_list)

# 2차원 이미지 데이터를 1차원 데이터로 변환
for num in range(0,10):
    for numbers in all_files[num]:
        img_path='./images/training/{0}/{1}'.format(num, numbers)
        print("load:" + img_path)
        img=Image.open(img_path)
        imgarr = np.array(img)
        for x in range(28):
            for y in range(28):
                if imgarr[y][x] > 0:
                    imgarr[y][x] = 1
                else:
                    imgarr[y][x] = 0
        # imgarr = np.array(img) / 255.0
        x_train_datas.append(np.reshape(imgarr, newshape=(784,1)))
        y_tmp = np.zeros(shape=(10))
        y_tmp[num] = 1
        y_train_datas.append(y_tmp)

# 테스트 데이터 수집을 위한 배열 선언
eval_files = []
x_test_datas=[]
y_test_datas=[]

# 폴더 안에 있는 테스트 이미지 로드 후 오름차순 정리
for i in range(0,10):
    path_dir = './images/testing/{0}'.format(i)
    file_list = os.listdir(path_dir)
    file_list.sort()
    eval_files.append(file_list)

# 2차원 이미지 데이터를 1차원 데이터로 변환
for num in range(0,10):
    for numbers in eval_files[num]:
        img_path='./images/testing/{0}/{1}'.format(num, numbers)
        print("load:" + img_path)
        img=Image.open(img_path)
        imgarr = np.array(img) / 255.0
        x_test_datas.append(np.reshape(imgarr, newshape=(784,1)))
        y_tmp = np.zeros(shape=(10))
        y_tmp[num] = 1
        y_test_datas.append(y_tmp)
# print(len(x_train_datas))
# print(len(y_train_datas))
# print(len(x_test_datas))
# print(len(y_test_datas))

x_train_datas = np.reshape(x_train_datas, newshape=(-1, 784))
y_train_datas = np.reshape(y_train_datas, newshape=(-1, 10))
x_test_datas = np.reshape(x_test_datas, newshape=(-1, 784))
y_test_datas = np.reshape(y_test_datas, newshape=(-1, 10))

## 인공지능 학습 모델 선정
input = tf.keras.Input(shape=(784), name="Input")
hidden = layers.Dense(512, activation="relu", name="Hidden1")(input)
output = layers.Dense(10, activation="softmax", name="Output")(hidden)

model = tf.keras.Model(inputs=[input], outputs=[output])
opt = keras.optimizers.Adam(learning_rate=0.005)
model.compile(loss='categorical_crossentropy', optimizer=opt, metrics=['accuracy'])
model.summary()


# 모델 트레이닝
history = model.fit(x_train_datas, y_train_datas, epochs=15, shuffle=True, validation_data=(x_test_datas, y_test_datas))
test_loss, test_acc = model.evaluate(x_test_datas, y_test_datas)
print('테스트 정확도:', test_acc)

# 트레이닝 후 이미지 정답 예측.
result = model.predict(x_test_datas)
diff = []
predictnum = []
for i in range(len(result)):
    if np.argmax(result[i]) != np.argmax(y_test_datas[i]):
        img = np.reshape(x_test_datas[i], newshape=(28,28))*255
        img = Image.fromarray(img)
        diff.append(img)
        predictnum.append(np.argmax(result[i]))

# 5번의 정답 맞추기
for i in range(5):
    pickidx = random.choice(range(0, len(diff)))
    print(predictnum[pickidx])
    diff[pickidx].show()
