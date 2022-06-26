## JavaScript 기초 정리 ✍ <br><br>
### 1. 자바스크립트 다이얼로그(대화 상자)의 종류. ⌨
 + ```prompt("메세지", "디폴트 입력값")``` 함수는, 아래 예시화면과 같이<br> 사용자로부터 문자열을 입력받아 리턴한다.
    ```javascript
        let ret = prompt("Write your name" , "Name");
        if(ret == null)
            // 취소 버튼이나 다이얼로그를 닫은 경우
        else if (ret == "")
            // 문자열 입력을 안하고, 확인 버튼을 누른 경우
        else
            // 문자열이 입력 될 경우.
    ```
    출력 시,<br><br>
    <img src="https://coursesweb.net/addons/javascript/imgs/prompt.jpg">

<br>

+  ```confirm("메세지")``` 함수는, 아래 예시화면과 같이 오로지 메세지와 OK / Cancel 버튼만 존재한다.
    ```javascript
    let ret = confirm("The result of 0+0 is 0 ?");
    if (ret == true)
        // 사용자가 "확인"버튼을 누를 경우
    else
        // 취소 버튼이나 다이얼로그를 닫은 경우
    ```
    출력 시,<br><br>
    <img src="https://coursesweb.net/addons/javascript/imgs/confirm.jpg">

<br>

+ ```alert("메세지")``` 함수는 단순히 다이얼로그를 출력하여 메세지를 전달하는 역할을 한다.
    ```javascript
        let ret = alert("환영합니다.");
    ```
    출력 시,<br><br>
    <img src = "https://coursesweb.net/addons/javascript/imgs/alert.jpg">

<br>

### 2. 자바스크립트의 증감연산자 ➕

+ 자바스크립트의 증감 연산자는 ```++ , --``` 총 두가지 이며,<br> 변수의 앞 또는 뒤에 붙어 값을 1 증가시키거나 1 감소시키는 역할을 한다.<br>

+ 이 때 연산자가 변수의 앞에 붙을 때 <b>전위 연산자</b>라 칭하고, <br> 변수의 뒤에 붙을 때 <b>후위 연산자</b>라 칭한다.<br>

+ 전위 연산자와 후위 연산자 모두 1씩 증가 또는 감소 시키는 역할을 하지만, <br> 연산 결과로 반환하는 값은 서로 다르다.<br>
다음 표를 확인하여 전위 연산자와 후위 연산자의 차이를 알아보자.

|연산자|```++a;```|```a++;```|
|:---:|:---|:---|
|연산자의 종류|전위 연산자|후위 연산자|
|내용|a를 1증가 시키고, 증가 전의 값을 반환|a를 1증가 시키고, 증가된 값을 반환|
|예시|``` [ a = 1; b = ++a ] a = 2, b = 2```|``` [ a = 1; b = a++ ] a = 2, b = 1```|

<br>

### 3. 자바스크립트의 조건문(if - else)를 삼항연산자로 변경하기.
JS의 삼항 연산자(조건 연산) 표현 방식.<br>
``` ( x > y ) ? True : False ```

```javascript
    <script>
    function if(){
        let x = 5;
        let y = 3;
            if(x > y) // x가 y보다 클 경우
                alert("x 가 y보다 큽니다.");
            else // x가 y보다 작을 경우
                alert("x가 y보다 작습니다.");  
    }
    
    function 삼항연산자(){
        let x = 5;
        let y = 3;
            let big = (x > y) ? "x 가 y보다 큽니다." : "x가 y보다 작습니다." ;
    }
    </script>
```

<br>

### 4. 자바스크립트의 문자열 연산 ➕

```javascript
문자열 연결
    "java" + "script"   // " javascript "
    "java" + 30         // " java30 "
    30     + "java"     // " 30java "
    30     + "500"      // " 30500 "
    30     + 500        // " 530 , 정수 계산 "

```

<br>

### 5. 자바스크립트의 대표적인 객체 📙 
+ **코어** 객체 <br>
코어 객체는, 기본 객체로서 ```Array, Date, String, Math``` 등의 타입이 있으며,<br>
웹 페이지나 웹 서버 응용프로그램 어디서나 사용 할 수 있다.<br>
+ **DOM**(Document Object Model) 객체<br>
DOM 객체는 HTML 페이지에 작성된 HTML 태그들을 브라우저가 하나씩 객체화 한 것들로,<br>
HTML 페이지의 내용과 모양을 제어하기 위해 사용되는 객체들이다.<br>
+ **BOM**(Browser Object Model) 객체<br>
BOM객체는,브라우저의 종류나 스크린의 크기 정보를 제공하거나,<br> 새 윈도우를 생성하는 등 브라우저와 관련된 객체들이다.

<br>

### 6. 문자열(String)들을 배열(Array)에 초기화 하는 방법 💬
배열이란, 여러 개의 데이터 값을 연속적으로 저장하고 전체를 하나의 단위로<br> 다루는 데이터 구조이다.<br>
다음과 같이 배열의 선언 및 초기화를 알아보자.
```javascript
    // 방법 1
    let food = ["Pizza" , "Chicken", "Sphagetti"];

    // 방법 2
    let food = new Array();
    food[0] = "Pizza";
    food[1] = "Chicken";
    food[2] = "Sphagetti";

    // 방법 3
    let food = new Array( "Pizza", "Chicken", "Sphagetti" );
```

<br>

### 7. Date 객체의 메소드명과 반환하는 값들의 특징 📅
```Date()``` 객체는 시간 정보를 담는 객체이다. Date 객체 또한 Array 처럼 new 를 이용하여 생성하며, <br>메소드의 종류들은 다음과 같다.

|메소드의 종류|설명|
|:---|:---:|
|getFullYear()|2018과 같이 4자리 년도 리턴|
|getMonth()|0 - 11 사이의 정수 리턴 ```( 1월 = 0 , 12월 = 11 )```|
|getDate()|한 달 내의 날짜 리턴```( 1 - 31 )```|
|getDay()|한주 내 요일들을 정수로 리턴 , ```( 일요일 = 0 , 토요일 = 6 )```|
|getHours()|0 - 23 사이의 정수 시간 리턴|
|getMinutes()|0 - 59 사이의 정수 분 리턴|
|getSeconds()|0 - 59 사이의 정수 초 리턴|
|getMilliseconds()|0 - 999 사이의 정수 밀리초 리턴|
|getTime()|1970년 1월 1일 0시 0분 0초 기준으로 현재 시간까지 경과된 밀리초 개수 리턴|

<br>

### 8. String(문자열) 객체의 메소드
```String(), 문자열```은 자바스크립트에서 제일 많이 사용되는 데이터로서, 무엇보다도 중요하다.<br>
문자열 객체의 생성은 다음과 같다.
```javascript
    // 두개의 경우 전부 같은 문자열 객체 생성이다.
    let hello = new String("Hello");
    let hello = "Hello";
```

문자열 객체의 주요 메소드
|메소드의 종류|설명|
|:---|:---:|
|charAt(index)|인덱스 idx에 있는 문자를 문자열로 만들어 리턴|
|charCodeAt(index)|인덱스 idx에 있는 문자의 16비트 유니코드 값 리턴|
|concat(s1, s2 ..., sN)|현재 문자열 뒤에 문자열 s1, s2, ... sN을 순서대로 연결한 새로운 문자열 생성|
|indexOf(s[,idx])|인덱스 idx 위치부터 문자열 s가 처음으로 나타나는<br> 인덱스 리턴.<br> idx가 생략되면 처음부터 검색, 발견할 수 없으면 -1 리턴|
|replace(strA,strB)|문자열 strA를 찾아 strB로 수정한 새로운 문자열 리턴|
|slice(idxA[,idxB])|인덱스 idxA에서 idxB 앞까지 문자열을 복사하여 리턴,<br> idxB가 생략되면 idxA에서 끝까지 리턴|
|substr(idxA[,len])|인덱스 idxA에서부터 len 문자 개수만큼<br> 새로운 문자열 리턴 len이 생략되면 문자열 끝까지 리턴|

#### slice() , substr()
```javascript
    let str = "Boys Like Girls";
    let a = str.slice(5,9);    // 인덱스 5에서 9 이전의 문자열 "Like" 리턴
    let b = str.substr(5,4); // 인덱스 5부터 4개의 문자로 구성된 "Like" 리턴
```

<br>

### 9. Math 객체
Math 객체는 수학 계산을 위해 사용되는 객체이다.<br>
Math 객체는 new Math()로 생성되지 않고, 다음과 같이 사용된다.<br>
``` Math.프로퍼티 or Math.메소드() ```

### 10. 난수 발생 Math.random()
```javascript
    for(let i = 0; i < 10 ; i++) {
        let num = Math.random() * 100;
        let n   = Math.floor(num);
        
        document.write(n + " ");
    }
```

다음은 Math를 이용해 구구단 연습을 하는 예제이다.
```javascript
    <script>
        function randomNum(){ // 1에서 9 사이의 난수 리턴
            return Math.floor(Math.random() * 9) + 1;
        }
        // 구구단 문제 생성
        let solution = randomNum() + "*" + randomNum();

        // 사용자로부터 정답 입력받기
        let user = prompt(solution + "값은 얼마에요 ?", 0);

        if(user == null){ // 취소 버튼을 누른 경우
            document.write("구구단을 종료합니다.");
        }
        else {
            let ans = eval(solution); // 구구단 정답 계산
            
            if(user == solution) // 정답과 사용자 입력값 비교
                documnet.write("정답 !");
            else
                document.wirte("오답 !");
        }
    </script>

```

<br>

### 11. DOM 객체들 사이의 관계
DOM 객체들은 DOM 트리에서 부모, 자식, 형제의 관계로 연결 되는데,<br>
이들은 다음 4개의 프로퍼티가 적용된다.
+ ```parentElement``` 프로퍼티 - 부모 객체
+ ```children``` 프로퍼티 - 직계 자식들의 컬렉션
+ ```firstElementChild``` 프로퍼티 - 첫 번째 직계 자식
+ ```lastElemenetChild``` 프로퍼티 - 마지막 직계 자식

아래 이미지를 참조하면 도움이 된다.

<img src="https://mblogthumb-phinf.pstatic.net/20140725_190/pjh445_1406257305706vbCqY_GIF/domtree7.gif?type=w2">

<br>

### 12. getElementById() , innerHTML

DOM 트리에서 특정 DOM 객체를 찾아내는 방법중 하나인,<br>
```getElementByID(idVal)``` 이다. document객체의 idVal 값을 가진 객체를 찾아 <br> 리턴한다. 다음 예시를 보자.
```javascript
    let p = document.getElementById("text"); 
    // text라는 id 값을 가진 DOM 객체 불러오기.

    p.style.color = "blue"; // p 객체의 색삭을 blue로 변경.
```
<br>

DOM 객체의 **innerHTML 프로퍼티**는 시작 태그와 종료 태그 사이에 <br> 들어있는 HTML 콘텐츠를 나타내는 문자열 정보이다. 

```javascript
    // 다음과 같이 innerHTML을 활용해 <p></p> 사이의 HTML 텍스트를 읽을 수 있다.
    let p = document.getElementById("text");
    let textinfo = p.innerHTML;

    // 다음 코드는 <p> 태그의 텍스트를 지우고 새로운 텍스트를 삽입한다.
    p.innerHTML = "안녕하세요.";
```
<br>

### 13. this

this는 객체 자신을 가리키는 자바스크립트 키워드로서,<br>
DOM 객체에서 자신을 가르키는 용도로 사용된다.<br>
다음 코드와 같이 활용되는 경우가 있다.
```javascript
    <div onclick=" this.style.color ='blue' ">
```
여기서 this는 div 객체의 자신을 가리키며, ```this.style.color = 'blue'```는 자신의 폰트 색상을 blue로 바꾼다.
<br>

### 14. getElementByTagName()
document 객체의 ```getElementByTagName()``` 메소드를 호출하면,<br>
동일한 HTML 태그 이름을 가진 DOM 객체들을 모두 찾아 컬렉션을 만든 후 리턴한다. <br>
다음 코드는 모든 ```<div>```태그를 찾는 코드이다.
```javascript
    let divTags = document.getElementByTagName = ("div");
    let len = divTags.length // <div> 태그의 모든 개수
```

<br>

### 15. ```open()``` , ```close()```

현재 브라우저 윈도우에 출력된 HTML 텍스트를 지워버리고 새로운 내용을 출력하면 먼저
```document.open()```을 호출하여 document 객체를 열어야 한다.<br>
'열기' 는 document 객체에 만들어진 현재 HTML 텍스트를 모두 지우고 새로운 HTML 텍스트를 받아들일 준비를 하는 것 이다.<br>
그러고 나서 ```document.write()```로 HTML 텍스트를 쓰고 `document.close()`를 호출하여 document 객체를 닫는다.
<br>다음 코드는 현재 브라우저 윈도우에 새로운 HTML 텍스트를 출력하는 코드이다.
```javascript
    document.open();
    document.write("<html><head>...<body> Hello World !");
    document.write(". . . .");
    document.write("</body></html>");
    document.close();
```

<br>

### 16. DOM 객체 동적 생성 ```createElement()```

`document.createElement("태그 이름")`을 통해<br> HTML 태그의 DOM 객체를 생성 할 수 있다.<br>
다음 코드는 `<div>` 태그의 DOM 객체를 생성하는 코드이다.
```javascript
    let newDiv = document.createElement("div");
    newDiv.innerHTML = "Hello World !"; 
    // 새로 생성된 div 태그에 innerHTML을 활용해 HTML 텍스트 삽입
    newDiv.setAttribute("id","idDiv");
    // 새로 생성된 div 태그의 id 값 지정
    newDiv.style.color = "blue";
    // 새로 생성된 div 태그의 CSS 스타일 지정
```
이를 활용해 DOM 트리에 삽입해 보자.<br>
보통은 대표적인 2가지 방법으로 활용된다.
+ `부모.appendChild(DOM 객체);  // DOM 객체를 부모의 마지막 자식으로 삽입 `
+ `부모.insertBefore(DOM 객체 [, 기준 자식])    // DOM 객체를 부모의 자식 객체중 기준 자식 앞에 삽입`
```javascript
    // id = "textBox" DOM 객체 찾기
    let p = document.getElementById("textBox");
    p.appendChild(newDiv);
```

<br>

### 17. Event 객체(이벤트 객체를 전달하기 위해 사용하는 키워드)
#### 이벤트 객체란?
이벤트가 발생하면 , 브라우저는 발생한 이벤트에 관련된 다양한 정보를 담은<br>
이벤트 객체를 만들어 이벤트 리스너에 전달한다.
<br>

#### 이벤트 객체 전달받기
+ 이름을 가진 이벤트 리스너 함수의 경우<br>
이벤트 리스너 함수는 다음과 같이 첫 번째 매개변수를 통해 <br>
이벤트 객체를 전달 받을 수 있다. 매개변수의 이름은 e 대신 다른 단어로 대체 가능하다.
```javascript
    function f(e){  // 매개변수 e에 이벤트 객체를 전달 받음.
        ...
    }
    obj.onclick = f; // obj 객체의 onclick 리스너로 함수 f 등록
```

+ 익명 함수의 경우<br>
익명 함수의 경우에도 다음과 같이 첫 번째 매개변수(e)를 통해 이벤트 객체를 전달받는다.
```javascript
    obj.onclick = function(e){  // 매개변수 e 생략 가능
        ...
    }
```

+ HTML 태그의 리스너<br>
HTML 태그에 리스너를 만드는 경우 이벤트 객체는 event라는 이름으로 전달 된다.
```javascript
    function f(e){
        ...
    }
    <button onclick="f(event)">버튼</button>
```

<br>

### 18. 이벤트의 디폴트 행동 취소 `preventDefault()`
HTML 태그중 몇몇 태그는, 특정 이벤트에 대한 **디폴트 행동**을 취한다.<br>
예) `<a>` 태그를 누르면 웹페이지를 로딩한다 , `submit` 버튼을 누르면 폼 데이터를 웹 서버로 전송한다 등<br>
이와 같이 디폴트 행동들을 자바스크립트를 통해 일어나지 않게 해줄 수 있는데<br>
2가지의 방법이 있다.
```javascript
    <a href="..." onclick = "return false"></a>
    // a를 클릭할 떄의 리턴 값 false

    <a href="..." onclick = "event.preventDefault()"></a>
    // 현재 발생한 이벤트의 디폴트 행동 취소
```

<br>

### 19. 마우스 이벤트 리스너의 종류 🖱
마우스와 관련된 이벤트 리스너가 호출되는 경우는 다음과 같다.
|종류|설명|
|:---|:---:|
|onmousedown|HTML 태그에 마우스 버튼을 누르는 순간|
|onmouseup|눌러진 마우스 버튼이 놓여지는 순간|
|onmousemove|마우스가 HTML 태그 위로 올라가는 순간. (자식 영역 포함)|
|onmouseout|마우스가 HTML 태그를 벗어나가는 순간. (자식 영역 포함)
|onmouseenter|마우스가 HTML 태그 위로 올라가는 순간 (이벤트 버블 단계 없음)
|onmouseleave|마우스가 HTML 태그 위로 벗어나는 순간 (이벤트 버블 단계 없음)
|onwheel|HTML태그에 마우스 휠이 구르는 동안 계속 호출|

다음 코드는 마우스 관련 이벤트 리스너 예제이다.
```javascript
<script>
    let width = 1;  //테두리 두께
    function down(obj){
        obj.style.fontStyle = "italic";
    }
    function up(obj){
        obj.style.fontStyle = "normal";
    }
    function over(obj){
        obj.style.borderColor = "violet";
    }
    function out(obj){
        obj.style.borderColor = "lightgray";
    }

    function wheel(e,obj){  // e는 이벤트 객체
        if(e.wheelDelta < 0)    // 휠을 아래로 굴릴 때
            width--;    // 테두리 두께 1 감소

        else    // 휠을 위로 굴릴 때
            width++;
        obj.style.borderStyle = "ridge";
        obj.style.borderwidth = width + "px";
    }
</script>
<html>
    <body>
        <div>마우스 관련
        <span   onmousedown = "down(this)"
                onmouseup = "up(this)"
                onmouseover = "over(this)"
                onmouseout = "out(this)"
                onwheel = "wheel(event,this)"
                style = "display:inline-block">
                이벤트</span>가 발생합니다.
        </div>
    </body>
</html>
```

<br>

### 20. `onmousemove()`
`onmousemove()` 리스너는 마우스가 움직이는 동안 계속 호출된다.<br>
마우스 이벤트 객체의 프로퍼티 종류를 먼저 알아보자.
|프로퍼티||
|:---|:---:|
|`x,y`| (x , y )는 타겟 객체의 부모 객체 내에서의 마우스 좌표
|`clientX, clientY`|( clientX, clientY )는 브라우저 윈도우의 문서 출력 영역 내에서의 마우스 좌표이다.
|`screenX, screenY`|( screenX, screenY )는 스크린을 기준으로 한 마우스 좌표이다.
|`offsetX, offsetY`| ( offsetX, offsetY )는 타겟 객체 내에서의 마우스 좌표이다. 

<br>

### 21. `oncontextmenu`
사용자가 홈페이지에서 HTML 태그위에 마우스 오른쪽 버튼을 클릭 할 때 나타나는 메뉴를<br>
컨텍스트 메뉴(context menu)라고 한다. 컨텍스트 메뉴에는 보통 소스 보기나<br>
이미지 다운로드 등의 기능을 둔다. 하지만 컨텍스트 메뉴가 출력 되기 전 `oncontextmenu` 리스너가 먼저 호출되므로 <br>
여기서 개발자가 특별한 작업을 할 수 있다.<br>
예를 들어 리턴 값을 `false` 로 주면 디폴트 행동을 취소하여 소스 보기나 이미지 저장을 할 수 없게 된다.<br>
```javascript
    document.oncontextmenu = function (){
        ...
        return false;   // 컨텍스트 메뉴 출력 금지
    }
``` 
**주의 !**   <br>
앞서 설명한, `preventDefault()`와 착각하면 안된다.<br>
`preventDefault`의 경우 `<a> , <submit>` 와 같이 디폴트 행동을 부정하는 기능인데 `oncontextmenu` 는 이와 다르다.

<br>

### 22. `onblur` , `onfocus` 🧿

`onblur`와 `onfocus`는 포커스(focus)가 변경될 때 호출된다.<br>포커스는 키 입력에 대한 독점권으로, 여러 개의 텍스트 창이나 라디오 버튼, 체크 박스 등이 있을 때<br>
마우스로 선택하면 그 폼 요소로 포커스가 옮겨가며 포커스를 가지고 있던 것은<br>
포커스를 잃게 된다. HTML 요소가 포커스를 받게 될 때 onfocus 리스너가 호출 되고<br>
포커스를 잃는 요소에는 onblur 리스너가 호출된다.

즉 , 요소가 포커스 받을 때엔 `onfocus`가<br>
요소가 포커스를 잃을 때엔 `onblur`가 호출된다고 보면 될 듯 하다.

<br>

### 23. `selectedIndex` 프로퍼티
`selectedIndex` 프로퍼티는 select 객체에서 사용하는 프로퍼티 이다.<br>
어떤 기능을 갖고있냐면 선택된 옵션의 인덱스 번호를 알아내는 프로퍼티인데,<br>
다음 코드처럼 사용이 가능하다.
```javascript
    <select id = "foodMenu">
        <option value="1">떡볶이</option>
        <option value="2" selected>김밥</option>
        <option value="3">순대</option>
    <select>

    <script>
        let select = document.getElementById("foodMenu");
        let index = select.selectedIndex;   
        /*  변수 index는 현재 선텍한 옵션의 대한 index값.
            즉 현재 기본적으로 선택돼있는 옵션은 김밥(=1) */
    </script>
```

<br>

### 24. 키 이벤트 중 `onkeydown` & `onkeypress` 리스너의 차이점

답은 간단하다. 아래 표를 참조하자.
|||
|:---|:---|
|`onkeydown`| **모든 키** 가 눌러지는 순간 호출되는 리스너
|`onkeypress`| **아스키 코드**값 만을 인식하기 때문에 `<enter> , <Space> , <Esc> `키와 문자키만 눌러지는 순간에 리스너 호출. <br>`<F1> , <Shift> , <Del> , <Ins>` 등 아스키코드에 없는 경우엔 호출되지 않는다.

### 25. `setTimeout()` ⌚
`setTimeout()` 메소드에 밀리초 단위의 타임아웃 지연 시간과 타임아웃 코드를 지정하면, <br>타임아웃 지연 시간이 지난 후 타임아웃 코드를 실행한다.<br>
다음 코드를 보자.
```javascript
setTimeout("alert('3초가 지났어요.')",3000);
// 3초가 지난뒤 alert 함수 호출
```
이와 같이 3000이라 지정한 이유는 `setTimeout` 메소드의 <br>기본 값이 밀리세컨드(ms) 이기 때문이다.<br>
다음 예제는 특정 시간이 지난 후 웹페이지로 연결되는 예제이다.

```javascript
<!DOCTYPE html>
<html>
<head>
</head>
<body>
<h3>이미지에 마우스를 올리고 5초간 그대로 있을 때 사이트로 이동합니다</h3>
<hr>
<img id="img" src="media/naver.gif" 
		onmouseover="startTimer(5000)"
		onmouseout="cancelTimer()">
<script>
let timerID=null;
function startTimer(time) {
	// 타이머 시작
	timerID = setTimeout("load('http://www.naver.com')", time);

	// 이미지에 마우스 올리면 나타내는 툴팁 메시지
	document.getElementById("img").title = "타이머 작동 시작...";
}
function cancelTimer() {
	if(timerID !=null) 
		clearTimeout(timerID); // 타이머 중단
}
function load(url) {
	window.location = url; // 현재 윈도우에  url 사이트 로드 
}
</script>
</body>
</html>
```

<br>

### 26. `setInterval()`
`setInterval()`은 타임아웃 시간 주기로 타임아웃 코드를 무한반복 실행하도록<br>
타이머를 설정한다. 이와 반대되는 코드로 `clearInterval()`는<br>
설정된 `setInterval()`의 타이머를 해제시킨다.<br>
다음 코드를 통해 알아보자.
```javascript
let timerID = setInterval("timeOutCode", msec)
/* timeOutCode = 타임아웃 자바스크립트 코드
   msec = 밀리세컨드(초) , 지연시간 */
clearInterval(timerID) // timerID의 setInterval를 초기화 한다.
```

다음 코드는 일정 시칸마다 텍스트를 옆으로 무한 반복 회전시키는 코드이다.

```javascript
<!DOCTYPE html>
<html>
<head><title>setInterval()로 텍스트 회전</title></head>
<body>
<div><span id="div" style="background-color:yellow">
					자동 회전하는 텍스트입니다.</span>
</div>
<script>
let div = document.getElementById("div");
let timerID = setInterval("doRotate()", 200); // 0.2초 주기로 doRotate() 호출

div.onclick = function (e) { // 마우스 클릭 이벤트 리스너
	clearInterval(timerID); // 타이머 해제. 문자열 회전 중단
}

function doRotate() {
	var str = div.innerHTML;
	var firstChar = str.substr(0, 1); // str 텍스트의 첫 문자
	var remains = str.substr(1, str.length-1); 
    // 첫 문자를 제외한 나머지 문자열

	str = remains + firstChar;
	div.innerHTML = str; // str 텍스트를 span 객체에 삽입.
}
</script>
</body>
</html>
```
