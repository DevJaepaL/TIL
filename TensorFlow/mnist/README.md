## Mnist를 사용하여 머신러닝 학습 ✏

숫자 ( 1 ~ 9 ) 사진의 픽셀값을 학습하여 숫자사진을 인식한다.

<a href="https://github.com/DevJaepaL/TIL/blob/main/TensorFlow/mnist/main.py"><b>Source code (GitHub)</b></a>

```python
#========= 라이브러리 모듈 import ========#
import os
from PIL import Image
import numpy as np
from tensorflow import keras
from keras import layers
import tensorflow as tf


all_files = []
Test_all_files = []

#======== 알아보고자 하는 이미지 폴더 리스트화 ========#
for i in range(0,10):
    path_dir = './images/training/{0}'.format(i)    # 알아보고자 하는 폴더
    file_list = os.listdir(path_dir)    # 폴더 내 정보를 리스트화
    file_list.sort()
    all_files.append(file_list)

#======== 테스트 데이터 이미지 폴더 리스트화 ========#
for j in range(0,10):
    Test_path_dir = './images/testing/{0}'.format(j)    # 알아보고자 하는 폴더
    Test_file_list = os.listdir(Test_path_dir)    # 폴더 내 정보를 리스트화
    Test_file_list.sort()
    Test_all_files.append(Test_file_list)

#========= 평가 데이터 세트 구성 ========#
x_trains_datas = []
y_trains_datas = []
for num in range(0,10):             # 0 - 9 폴더
    for numbers in all_files[num]:  # 각 폴더 안에 있는 파일명
        img_path = './images/training/{0}/{1}'.format(num,numbers)
        print("Load : " + img_path)
        img = Image.open(img_path)
        imgArr = np.array(img) / 255.0
        x_trains_datas.append(np.reshape(imgArr, newshape=(784,1)))
        y_tmp = np.zeros(shape=(10))
        y_tmp[num] = 1
        y_trains_datas.append(y_tmp)

#========= 테스트 데이터 세트 구성 ========#
x_test_datas = []
y_test_datas= []
for num in range(0,10):             # 0 - 9 폴더
    for numbers in Test_all_files[num]:  # 각 폴더 안에 있는 파일명
        Test_img_path = './images/testing/{0}/{1}'.format(num,numbers)
        print("Load : " + Test_img_path)
        Test_img = Image.open(Test_img_path)
        Test_imgArr = np.array(Test_img) / 255.0
        x_test_datas.append(np.reshape(Test_imgArr, newshape=(784,1)))
        y_test_tmp = np.zeros(shape=(10))
        y_test_tmp[num] = 1
        y_test_datas.append(y_test_tmp)

#======== 학습 ========#
x_trains_datas = np.reshape(x_trains_datas, newshape=(-1,784))
y_trains_datas = np.reshape(y_trains_datas, newshape=(-1,10))

input = tf.keras.Input(shape=(784), name="Input")
hidden = layers.Dense(512, activation="relu", name="Hidden1")(input)
output = layers.Dense(10, activation="softmax", name="Output")(hidden)

model = tf.keras.Model(inputs=[input], outputs=[output])
opt = keras.optimizers.Adam(learning_rate=0.001)
model.compile(loss='categorical_crossentropy',
              optimizer=opt, metrics=['accuracy'])
model.summary()
model.fit(x_trains_datas, y_trains_datas, epochs=15, shuffle=True)

#======== 평가 ========#
x_test_datas = np.reshape(x_test_datas, newshape=(-1,784))
y_test_datas = np.reshape(y_test_datas, newshape=(-1,10))
test_loss, test_acc = model.evaluate(x_test_datas, y_test_datas)
print('테스트 정확도 : ', test_acc)
```
### 22-09-16
`Loss` 값 및 테스트 정확도만 확인하였고, <br>
이미지를 실제로 학습하여 맞추는지는 추후 코드를 새로 작성해보려고 함.
