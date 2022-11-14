import os.path

import matplotlib.pyplot as plt
import numpy as np
import PIL
import tensorflow as tf
from tensorflow import keras
import pathlib
import os

# 데이터 세트 다운로드
dataset_url = "https://storage.googleapis.com/download.tensorflow.org/example_images/flower_photos.tgz"
data_dir = tf.keras.utils.get_file('flower_photos', origin=dataset_url, untar=True)
data_dir = pathlib.Path(data_dir)

# 데이터 검사 및 이해
# print(data_dir)
image_count = len(list(data_dir.glob('*/*.jpg')))
# print(image_count)

roses = list(data_dir.glob('roses/*'))
im = PIL.Image.open(str(roses[1]))
# im.show()

tulips = list(data_dir.glob('tulips/*'))
im = PIL.Image.open(str(tulips[0]))
# im.show()

im = PIL.Image.open(str(tulips[1]))
# im.show()

# 입력 파이프라인 빌드 - 데이터 세트 만들기
batch_size = 32
img_height = 180
img_width = 180

# 이미지 전처리
train_ds = tf.keras.preprocessing.image_dataset_from_directory(
    data_dir,
    validation_split=0.2,
    subset="training",
    seed=123,
    image_size=(img_height, img_width),
    batch_size=batch_size)

val_ds = tf.keras.preprocessing.image_dataset_from_directory(
    data_dir,
    validation_split=0.2,
    subset="validation",
    seed=123,
    image_size=(img_height, img_width),
    batch_size=batch_size)

class_names = train_ds.class_names
print(class_names)

# 데이터 시각화
# plt.figure(figsize=(10, 10))
# for images, labels in train_ds.take(1):
#     for i in range(9):
#         ax = plt.subplot(3, 3, i + 1)
#         plt.imshow(images[i].numpy().astype("uint8"))
#         plt.title(class_names[labels[i]])
#         plt.axis("off")
# plt.show()

# 데이터 Shape 확인
for image_batch, labels_batch in train_ds:
    print(image_batch.shape)
    print(labels_batch.shape)
    break

# 데이터 I / O(입출력)
AUTOTUNE = tf.data.experimental.AUTOTUNE
train_ds = train_ds.cache().shuffle(1000).prefetch(buffer_size=AUTOTUNE)
val_ds = val_ds.cache().prefetch(buffer_size=AUTOTUNE)

# 전처리
normalization_layer = tf.keras.layers.experimental.preprocessing.Rescaling(1. / 255)
normalized_ds = train_ds.map(lambda x, y: (normalization_layer(x), y))
image_batch, labels_batch = next(iter(normalized_ds))
first_image = image_batch[0]
print(np.min(first_image), np.max(first_image))

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

# 개선 후 데이터 시각화
# plt.figure(figsize=(10, 10))
# for images, _ in train_ds.take(1):
#     for i in range(9):
#         augmented_images = data_augmentation(images)
#         ax = plt.subplot(3, 3, i + 1)
#         plt.imshow(augmented_images[0].numpy().astype("uint8"))
#         plt.axis("off")
#     plt.show()

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
    tf.keras.layers.Flatten(),
    tf.keras.layers.Dense(128, activation='relu'),
    tf.keras.layers.Dense(num_classes)
])

model.compile(optimizer='adam',
              loss=tf.keras.losses.SparseCategoricalCrossentropy(from_logits=True),
              metrics=['accuracy'])

model.summary()

# 모델 학습 횟수 선정(= 100번)
epochs = 100
history = model.fit(
    train_ds,
    validation_data=val_ds,
    epochs=epochs
)

# 모델 테스트 / 평가 시각화
acc = history.history['accuracy']
val_acc = history.history['val_accuracy']

loss = history.history['loss']
val_loss = history.history['val_loss']

epochs_range = range(epochs)

plt.figure(figsize=(8, 8))
plt.subplot(1, 2, 1)
plt.plot(epochs_range, acc, label='Training Accuracy')
plt.plot(epochs_range, val_acc, label='Validation Accuracy')
plt.legend(loc='lower right')
plt.title('Training and Validation Accuracy')

plt.subplot(1, 2, 2)
plt.plot(epochs_range, loss, label='Training Loss')
plt.plot(epochs_range, val_loss, label='Validation Loss')
plt.legend(loc='upper right')
plt.title('Training and Validation Loss')
plt.show()

# 모델 저장
# checkpoint_path = "training_1/cp-{epoch:04d}.ckpt"
# checkpoing_dir = os.path.dirname(checkpoint_path)
#
# # 모델 가중치 저장하는 콜백
# cp_callback = tf.keras.callbacks.ModelCheckpoint(filepath=checkpoint_path,
#                                                  save_weights_only=True,
#                                                  period=1,
#                                                  verbose=1)
# # 가중치 저장
# model.save_weights(checkpoint_path.format(epoch=0))
#
# # 새로운 콜백으로 모델 훈련
# model_history = model.fit(train_ds, val_ds,
#                           epochs=100,
#                           batch_size=batch_size,
#                           validation_data=(train_ds, val_ds),
#                           callbacks=[cp_callback],
#                           verbose=1)
