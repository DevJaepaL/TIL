# 사용자가 입력한 숫자까지 포함하여 소수를 구해 출력.
# 소수의 정의는 '정수 1과 자기 자신의 값으로 밖에 나누어지지 않는 정수' 이다.
# 입력받은 값 소수 구분하는 함수
def primeNum(inputNum):
    if inputNum != 1: # 1은 소수가 아니기 때문에 입력 값이 1이 아닐 경우.
        for i in range(2,inputNum): # 2 -> 입력 값까지 반복
            if inputNum % i == 0: # 반복중 입력 값이 나머지 값이 0 일 경우.
                return False # 소수가 아니기 때문에 거짓 값(= False) 반환.
    else:
        return False # 입력 값이 1이기 때문에 거짓 값(= False) 반환.

    return True # 이 외의 모든 정수는 소수이기 때문에 참 값(= True) 반환.

if __name__ == "__main__":
    inputNum = int(input("숫자를 입력 하세요 : ")) # 정수 입력
    numList = (i for i in range(2, inputNum + 1)) # 입력 값을 토대로 정수 리스트 생성
    primeResult = [] # 소수값 담을 배열 생성
    for num in numList: # num == 2 이다.
        if primeNum(num):
            primeResult.append(num)

    print("입력 값 : {0} " .format(inputNum))
    print("입력 값 {0} 까지의 소수 값은 ==> {1} ".format(inputNum, primeResult))