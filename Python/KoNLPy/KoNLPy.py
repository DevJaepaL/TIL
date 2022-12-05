# 형태소 추출
from konlpy.tag import Okt

okt = Okt()
token = okt.morphs('폐허가 된다 해도')
print(token)

# 품사 정보 추가
from konlpy.tag import Okt

okt = Okt()
sentence_tag = okt.pos('폐허가 된다 할지라도')
print(sentence_tag)

sentence_tag = okt.pos('폐허가 된다 할지라도', join=True)
print(sentence_tag)

# 어구 추출
from konlpy.tag import Okt

okt = Okt()
sentence = okt.phrases('인공지능 전처리는 재밌다')
print(sentence)

# 토큰화 실습(형태소 분석)
from konlpy.tag import Okt

okt = Okt()
verse = '빨래를 해야겠어요 오후엔 비가 올까요 그래도 상관은 없어요 괜찮아요 ' \
        '뭐라도 해야만 할 것 같아요 그러면 나을까 싶어요' \
        '잠시라도 모두 잊을 수 있을 지 몰라요 ' \
        '그게 참 마음처럼 쉽지가 않아서 그게 참 말처럼 되지가 않아서 ' \
        '무너진 가슴이 다시 일어설 수 있게 ' \
        '난 어떡해야 할까요 어떻게 해야만 할까요 ' \
        '그대가 날 떠난 건지 내가 그댈 떠난 건지 ' \
        '일부러 기억을 흔들어 뒤섞어도' \
        '금세 또 앙금이 가라앉듯 다시금 선명해져요' \
        '잠시라도 모두 잊을 수 있을까 했는데' \
        '그게 참 마음처럼 쉽지가 않아서' \
        '그게 참 말처럼 되지가 않아서 ' \
        '무너진 가슴이 다시 일어설 수 있게 ' \
        '난 어떡해야 할까요 어떻게 해야만 할까요 ' \
        '뒤집혀버린 마음이 사랑을 쏟아내도록 ' \
        '그래서 아무 것도 남김 없이 비워내도록 ' \
        '나는 이를 앙 다물고 버텨야 했죠 ' \
        '하지만 여태 내 가슴 속엔' \
        '그게 참 말처럼 쉽게 되지가 않아서' \
        '무너진 가슴이 다시 일어설 수 있게' \
        '난 어떡해야 할까요 어떻게 해야만 할까요' \
        '빨래를 해야겠어요 오후엔 비가 올까요'

sentence_tag = okt.pos(verse)
print(sentence_tag)

# 동일한 자료가 몇 개인지 빈도수 파악
import collections

adj_list = []
for word, tag in sentence_tag:
    if tag in ['Noun', 'Adjective']:
        adj_list.append(word)

counts = collections.Counter(adj_list)
tag = counts.most_common(10)  # 가사에서 많이 사용된 상위 10개의 단어
print(tag)

# Wordcloud 생성

from konlpy.tag import Okt
from wordcloud import WordCloud
import collections
import matplotlib.pyplot as plt

okt = Okt()
verse = '빨래를 해야겠어요 오후엔 비가 올까요 그래도 상관은 없어요 괜찮아요 ' \
        '뭐라도 해야만 할 것 같아요 그러면 나을까 싶어요' \
        '잠시라도 모두 잊을 수 있을 지 몰라요 ' \
        '그게 참 마음처럼 쉽지가 않아서 그게 참 말처럼 되지가 않아서 ' \
        '무너진 가슴이 다시 일어설 수 있게 ' \
        '난 어떡해야 할까요 어떻게 해야만 할까요 ' \
        '그대가 날 떠난 건지 내가 그댈 떠난 건지 ' \
        '일부러 기억을 흔들어 뒤섞어도' \
        '금세 또 앙금이 가라앉듯 다시금 선명해져요' \
        '잠시라도 모두 잊을 수 있을까 했는데' \
        '그게 참 마음처럼 쉽지가 않아서' \
        '그게 참 말처럼 되지가 않아서 ' \
        '무너진 가슴이 다시 일어설 수 있게 ' \
        '난 어떡해야 할까요 어떻게 해야만 할까요 ' \
        '뒤집혀버린 마음이 사랑을 쏟아내도록 ' \
        '그래서 아무 것도 남김 없이 비워내도록 ' \
        '나는 이를 앙 다물고 버텨야 했죠 ' \
        '하지만 여태 내 가슴 속엔' \
        '그게 참 말처럼 쉽게 되지가 않아서' \
        '무너진 가슴이 다시 일어설 수 있게' \
        '난 어떡해야 할까요 어떻게 해야만 할까요' \
        '빨래를 해야겠어요 오후엔 비가 올까요'

sentence_tag = okt.pos(verse)

adj_list = []
for word, tag in sentence_tag:
    if tag in ['Noun', 'Adjective']:
        adj_list.append(word)

counts = collections.Counter(adj_list)
tag = counts.most_common(50)
print(tag)

font_path = 'C:/Windows/Fonts/malgunbd.ttf'
wc = WordCloud(font_path=font_path, background_color='black', max_font_size=60)
cloud = wc.generate_from_frequencies(dict(tag))

plt.imshow(cloud)
plt.show()

# 영화 리뷰 자연어 처리
from konlpy.tag import Okt
import collections
import matplotlib.pyplot as plt
from wordcloud import wordcloud
import pandas as pd
import os

# 파일 사이즈 체크
file_size = os.path.getsize('./data/ratings.txt')
print('File Size : ', file_size, 'bytes')
df = pd.read_table('./data/ratings.txt')

# 하나의 아이디로 같은 영화의 리뷰를 여러번 반복 했는지 확인
# Null 값이 있는지 확인
print(df['id'].nunique())
print(df.isnull().sum())

# 결측치 제거
df = df.dropna(how='any')
print(df.isnull().sum())

# 불용어 제거
df['document'] = df['document'].str.replace("[^ㄱ-ㅎㅏ-ㅣ가-힣 ]", "")
print(df)

# 형태소 토큰화
okt = Okt()
temp_list = []
for sentence in df['document']:
    s_list = okt.pos(sentence)
    for word, tag in s_list:
        if tag in ['Noun', 'Adjective']:
            temp_list.append(word)

counts = collections.Counter(temp_list)
tag = counts.most_common(50)
print(tag)

# 시각화
font_path = 'C:/Windows/Fonts/malgunbd.ttf'
wc = WordCloud(font_path=font_path, background_color='black', max_font_size=60)
cloud = wc.generate_from_frequencies(dict(tag))
plt.imshow(cloud)
plt.show()

# 불용어 제거
stopword = ['점', '정말', '왜', '말', '그', '없다', '정도', '걸', '뭐', '이건',
            '영화', '완전', '좀', '있는', '거', '나', '이', '볼', '입니다'
    , '것', '이런', '더', '수', '때']

list = []
for sentence in df['document']:
    s_list = okt.pos(sentence)
    for word, tag in s_list:
        if word not in stopword:
            if tag in ['Noun', 'Adjective']:
                list.append(word)

counts = collections.Counter(list)
tag = counts.most_common(50)

# 재 시각화
font_path = 'C:/Windows/Fonts/malgunbd.ttf'
wc = WordCloud(font_path=font_path, background_color='black', max_font_size=60)
cloud = wc.generate_from_frequencies(dict(tag))
plt.imshow(cloud)
plt.show()
