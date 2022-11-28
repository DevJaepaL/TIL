
# 데이터 전처리(Data Preprocessing)

+ 머신러닝 모델에 훈련 데이터를 입력하기 전에 데이터를 가공.
+ 파이썬의 `Numpy`나 `Pandas` 라이브러리와 같은 머신러닝의 핵심 도구, 
`Matplotlib`와 `Seaborn` 같은 데이터 시각화 도구를 활용하여 실제 데이터를 정리이다.
+ 머신러닝 기초 수식
  `y = f(X)`
+ 데이터 X 를 머신러닝 함수 f()에 넣으면 그 결과 y가 나온다.
+ 데이터 X는 훈련 데이터(train data)와 테스트 데이터(test data)가 모두 같은 구조를 갖는 피쳐(Feature)이어야 한다.

## 데이터 품질 문제

### 데이터 분포의 지나친 차이
+ 데이터가 연속형 값인데 최대값과 최소값 차이가 피쳐보다 더 많이 나는 경우이다.
+ 학습에 영향을 줄 수 있기 때문에 데이터의 스케일(Sclae)을 맞춰 준다.
  + 데이터의 최댓값과 최솟값을 0에서 1 사이 값으로 바꾸거나 표준 정규분포 형태로 나타내는 등과 같다.

### 기수형 데이터와 서수형 데이터
+ 기수형 데이터와 서수형 데이터는 일반적으로 숫자로 표현되지 않는다.
+ 컴퓨터가 이해할 수 있는 숫자 형태의 정보로 변형한다.

### 결측치(Missing data)
+ 실제로 존재하지만 데이터베이스 등에 기록되지 않는 데이터이다.
+ 해당 데이터를 빼고 모델을 돌릴 수 없기 때문에 결측치 처리 전략을 세워 데이터를 채워 넣는다.

### 이상치(Outlier)

+ 극단적으로 크거나 작은 값이다.
+ 단순히 데이터 분포의 차이와는 다르다.
+ 데이터 오기입이나 특이 현상 때문에 나타난다.

### One-Hot-Encoding
+ 범주형 데이터의 개수만큼 가변수(Dummy variable)를 생성하여 존재 유무를 1 또는 0으로 표현한다.
+ Color 라는 변수에 {Green , Blue, Yellow} 3개의 값이 있을 때 
3개의 가변수를 만들고 각 색상에 인덱스를 지정한다.
+ 해당 값이면 1, 아니면 0을 입력 한다.
+ 원핫인코딩을 적용하려면 `Pandas`에서 제공하는 `get_dummies` 함수나
사이킷런(Scikit-Learn)에서 제공하는 LabelEncoder나 OneHotEncoder를 이용한다.

### 바인딩(Binding) : 연속형 데이터를 범주형 데이터로 변환

`Python`으로 표현한 코드이다.

```python
raw_data = {'regiment': ['Nighthawks', 'Nighthawks',
                         'Nighthawks', 'Nighthawks', 'Dragoons', 'Dragoons',
                         'Dragoons', 'Dragoons', 'Scouts', 'Scouts', 'Scouts',
                         'Scouts'],
            'company': ['1st', '1st', '2nd', '2nd', '1st',
                        '1st', '2nd', '2nd', '1st', '1st', '2nd', '2nd'],
            'name': ['Miller', 'Jacobson', 'Ali', 'Milner',
                     'Cooze', 'Jacon', 'Ryaner', 'Sone', 'Sloan', 'Piger',
                     'Riani', 'Ali'],
            'preTestScore': [4, 24, 31, 2, 3, 4, 24, 31, 2, 3,
                             2, 3],
            'postTestScore': [25, 94, 57, 62, 70, 25, 94, 57,
                              62, 70, 62, 70]}
df = pandas.DataFrame(raw_data, columns=['regiment',
                                     'company', 'name', 'preTestScore', 'postTestScore'])
```
postTestScore에 대한 학점을 측정하는 코드를 작성한다. 
  1. 데이터 범위를 구분 : 0-25 , 25 - 50, 50 - 75, 75 - 100 으로 구분한다. 
  2. 함수 `Cut` 사용
  3. bins 리스트에 구간의 시작 값, 끝 값을 넣고 구간의 이름을 리스트로 나열한다.
  4. bins의 원소는 5개이고, group_names는 4개이다.
  5. cut 함수로 나눌 시리즈 객체와 구간, 구간의 이름을 넣어주면 해당 값을 바인딩하여 표시해준다.

```python
import pandas as pd
bins = [0, 25, 50, 75, 100] # bins 정의(0-25, 25-50, 60-75, 75-100)
group_names = ['Low', 'Okay', 'Good', 'Great']
categories = pd.cut(df['postTestScore'], bins, labels=group_names)
print(categories)
```

**결과**

![](https://velog.velcdn.com/images/jaepal/post/595622e9-eb5b-4f14-8146-43b755be321e/image.PNG)


## 데이터의 크기 맞추기(Feature Scaling)

스케일링(Scaling) : 데이터 간 범위를 맞추는 것
+ 몸무게와 키를 하나의 모델에 넣으면 데이터의 범위가 훨씬 넓어져 
키가 몸무게에 비해 모델에 과다하게 영향을 준다.
+ x1 과 x2의 변수 범위가 다를 때 하나의 변수 범위로 통일 시켜 처리한다.
![](https://velog.velcdn.com/images/jaepal/post/42dfaa8a-03bf-4e81-9c60-5edc8775ce8e/image.PNG)

+ 최소값 - 최대값 정규화 (Min - Max Normalization):
최소값과 최대값을 기준으로 0에서 1, 또는 0에서 지정 값 까지로 값의 크기를 변화시킨다.
![](https://velog.velcdn.com/images/jaepal/post/ce6cb07f-a73d-4732-b8eb-9bd62a9e1063/image.PNG)

+ x는 처리하고자 하는 열, `x _ i` 는 이 열 하나의 값, `max(x)`는 해당 열의 최댓값, `min(x)`는 해당 열의 최솟값
+ `new_max` 와 `new_min`은 새롭게 지정되는 값의 최대값 또는 최소값이다.

### Z-Score Normalization

기존 값을 표준 **정규분포값으로 변환**하여 처리한다.
![](https://velog.velcdn.com/images/jaepal/post/417104dd-7ce7-495e-b454-9b8d5aad1040/image.PNG)

+ μ는 x 열의 평균값이고 σ는 표준편차이다.
+ **통계학 시간에 배우는 수식**과 동일하다.

## 머신러닝 프로세스와 데이터 전처리

다음은 머신러닝의 프로세스이다.

![](https://velog.velcdn.com/images/jaepal/post/d3b18099-5107-4d42-86e3-2fc753e512ac/image.jpeg)


1. **데이터를 확보**한 후 데이터를 **정제하고 전처리**한다.
2. **학습용과 테스트 데이터**를 나누어 **학습용 데이터로 학습**을 실시한다.
3. **학습결과**를 평가지표와 비교하여 **하이퍼 매개변수로 변환**한다.
4. 최종적인 모델 생성하여 테스트 데이터세트로 **성능을 측정**한다.
5. 모델을 시스템에 배치하여 **모델을 작동**시킨다.

머신러닝을 학습 시키는 단계에서 데이터 정제 및 데이터 전처리 단계가 가장 많은 시간이 들어가는 작업이다.

