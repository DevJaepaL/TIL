## 모듈 로드 ##
import tensorflow as tf
from tensorflow import keras
import numpy as np
import matplotlib.pyplot as plt

## 데이터 불러오기 ##
imdb = keras.datasets.imdb
(train_data,train_lables),(test_data,test_lables) = imdb.load_data(num_words=10000)

## 훈련 데이터와 정답의 개수 일치한지 확인 ##
# print("훈련 샘플 개수 : ' {} ' , 레이블(정답) 개수 : ' {} ' " .format(len(train_data),len(train_lables)))
# print(len(train_data[0]),len(train_data[1]))

## 단어였던 정수 시퀀스를, 다시 단어로 변환 ##
word_index = imdb.get_word_index()
word_index = {k:(v+3) for k,v in word_index.items()}
word_index["<PAD>"] = 0
word_index["<START>"] = 1
word_index["<UNK>"] = 2     # Unknown
word_index["<UNUSED>"] = 3

reverse_word_index = dict([(value,key) for (key, value) in word_index.items()])

## 리뷰 해석 출력 함수 ##
def decode_review(text):
    return ' '.join([reverse_word_index.get(i,'?') for i in text])

print(decode_review(train_data[0]))

## 리뷰 데이터 전처리 및 학습 준비 ##

# 문장 내 검색 단어값 최대 256개 까지만 찾도록 검색한다.
train_data = keras.preprocessing.sequence.pad_sequences(train_data,
                                                        value = word_index["<PAD>"],
                                                        padding = 'post',
                                                        maxlen = 256)

test_data = keras.preprocessing.sequence.pad_sequences(test_data,
                                                       value = word_index["<PAD>"],
                                                       padding = 'post',
                                                       maxlen = 256)

## 모델 구성 준비 ##

vocab_size = 10000
model = keras.Sequential()

model.add(keras.layers.Embedding(vocab_size,2,input_shape=(None,))) # Embedding 레이어 값 2개 적용
model.add(keras.layers.GlobalAveragePooling1D())
model.add(keras.layers.Dense(4, activation='relu')) # 입력 레이어 4개
model.add(keras.layers.Dense(1, activation='sigmoid'))
model.summary()

# 손실 함수 및 옵티마이저 적용
model.compile (optimizer= 'adam',
               loss= 'binary_crossentropy',
               metrics= ['accuracy'])

# 훈련세트 , 테스트세트 , 검증세트 구성
x_val = train_data[:10000]
partial_x_train = train_data[10000:]

y_val = train_lables[:10000]
partial_y_train = train_lables[10000:]

history = model.fit(
    partial_x_train,
    partial_y_train,
    epochs = 120,
    batch_size = 512,
    validation_data = (x_val,y_val),
    verbose = 1
)

## 모델 평가 준비 ##
results = model.evaluate(test_data, test_lables, verbose = 2)
print(results)

history_dict = history.history
print(history_dict.keys())

acc = history_dict['accuracy']
val_acc = history_dict['val_accuracy']
loss = history_dict['loss']
val_loss = history_dict['val_loss']

epochs = range(1,len(acc) + 1)

## 모델 훈령 평가 시각화 (그래프화) ##

# "bo" 는 "파란색 점"
plt.plot(epochs,loss,'green', label='Training Loss')
# b 는 "파란 실선"
plt.plot(epochs, val_loss, 'black', label='Validation Loss')
plt.title('Training and Validation Loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.show()

## 그래프 초기화 후 정확도 값 그래프 시각화 ##
plt.clf()
plt.plot(epochs, acc, 'green', label='Training Acc')
plt.plot(epochs, val_acc, 'black', label='Validation Acc')
plt.title('Training and Validation accuracy')
plt.xlabel('Epochs')
plt.ylabel('Accuracy')
plt.legend()
plt.show()