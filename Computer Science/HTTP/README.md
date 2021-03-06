# HTTP π°οΈ

> HTTP (HyperText Transfer Protocol)

HTTPλ κ°λ¨νκ² **λ¬Έμλ₯Ό μ μ‘νκΈ° μν μ½μ**μ λ»ν©λλ€.
λͺ¨λ  νλ‘κ·Έλ¨μ μ΄ μ½μμ μ§ν€λ©° κ°λ°νμ¬ 
μννκ² μ¬μ©μκ° μ λ³΄λ₯Ό κ΅νν  μ μκ² ν΄μ€λλ€.

κΉκ² λ€μ΄κ°λ©΄,

![](https://velog.velcdn.com/images/jaepal/post/f76b2e66-fbfb-4b7d-9f46-bab5c4613ed2/image.png)


**μμ²­(Request)** κ³Ό **μλ΅(Response)** μΌλ‘ κ΅¬μ± λΌμμΌλ©° μ΄λ₯Ό ν΅ν μμλ‘
μ¬μ©μκ° μΉ νμ΄μ§μμ λ§ν¬κ° λ΄κ²¨μλ νμ€νΈ λλ μ΄λ―Έμ§λ₯Ό ν΄λ¦­(**Request**)νλ©΄
ν΄λΉ μΉ νμ΄μ§λ‘ λμ΄κ°λλ€(**Response**).

λ°λΌμ μ μ λ€μ΄ μΉ λΈλΌμ°μ λ₯Ό μ¬μ©νλ©΄ λ³Ό μ μλ μΈν°λ· μ£Όμ λ§¨ μμ ν­μ μ‘΄μ¬νλ
**http://** κ° λ°λ‘ ν΄λΉ νλ‘ν μ½μ μ΄μ©ν΄ νμν μ λ³΄λ₯Ό κ΅ννκ² λ€λ μλ―Έμλλ€.
μΆν, λ³΄μμ±μ κ°νν λ²μ (TLSλ₯Ό ν΅ν΄)μΈ **HTTPS**λ λ±μ₯νκ² λ©λλ€.

## μμ²­(Request) π€

**ν΄λΌμ΄μΈνΈ(μ¬μ©μ)κ° μλ²μκ² μνλ μ λ³΄λ₯Ό μμ²­** νλκ².
**μλ²(Server)** κ° μ λ³΄κ° λ΄κΈ΄ λ¬Έμλ₯Ό λ°κ³  **ν΄λΌμ΄μΈνΈ(Client)** κ° μ΄λ€ κ²μ
μνλμ§ νμν©λλ€.

μ°λ¦¬κ° μΌμμμ μ νλ μμλ‘, 
λ μ€ν λμ΄λ μλΉμμ μ°λ¦¬κ° μνλ λ©λ΄λ€μ μ£Όλ¬Έμμ μ μ΄ 
μλ¦¬μ¬μκ² μ λ¬νλ κ²μ μκ°νλ©΄ λ©λλ€.

### μμ²­μ λ©μλ μ’λ₯(Request Method)

+ **GET** : μλ£λ₯Ό **μμ²­** ν λ μ¬μ©ν©λλ€.
+ **POST** : μλ£μ **μμ±** μ μμ²­ν  λ μ¬μ©ν©λλ€.
+ **PUT** : μλ£μ **μμ ** μ μμ²­ν  λ μ¬μ©ν©λλ€.
+ **DELECT** : μλ£μ **μ­μ ** λ₯Ό μμ²­ν  λ μ¬μ©ν©λλ€.
+ **TRACE** : μλ£μ λ΄μ©μ **λ°ν** μ μμ²­ν  λ μ¬μ©ν©λλ€.
+ **CONNECT** : ν΄λΌμ΄μΈνΈκ° νΉμ  μ’λ₯μ **νλ‘μ μλ²μκ² μ°κ²°** μ μμ²­ν  λ μ¬μ©ν©λλ€.

μ΄μΈμλ μ¬λ¬ μ’λ₯μ λ©μλκ° μμ΅λλ€.

### μμ²­(Get) λ©μΈμ§μ μμ

```http
GET https://velog.io/@jaepal HTTP/1.1	// μμ²­μ μμ
User-Agent: Mozilla/5.0 (Windows NT 11.0; win64; x64) ...	// ν€λ
Upgrade-Insecure-Requests : 1

```

#### 1. μμ²­μ μμ(μ²« μ€)

μ²«λ²μ§Έ μ€μ **λ©μλ κ΅¬μ‘° λ²μ **μΌλ‘ κ΅¬μ± λΌμμ΅λλ€.

+ GET : HTTP Method
+ <a href="https://velog.io/@jaepal"></a> : μ¬μ΄νΈ μ£Όμ
+ HTTP/1.1 : HTTPμ λ²μ 

#### 2. ν€λ(λλ²μ§Έ μ€ λΆν°)

λλ²μ§Έ μ€ λΆν°λ ν€λμ΄λ©°, **μμ²­ν μ λ³΄** λ€μ λ΄κ³  μμ΅λλ€.
`User-Agent`, `Upgrade-Insecure-Request` λ±μ΄ ν€λμ ν΄λΉλλ©°
ν€λλ€μ μ’λ₯λ λ§€μ° λ€μν©λλ€.

#### 3. λ³Έλ¬Έ

λ³Έλ¬Έμ **μμ²­**μ ν  λ λ³΄λΌ **λ°μ΄ν°**λ€μ λ΄λ μ­ν μ ν©λλ€.
μλ¨ μμμμλ μ£Όμμ μμ²­λ§μ λ³΄λ΄κ³  μκ³  λ°μ΄ν°λ₯Ό λ³΄λ΄μ§ μκΈ° λλ¬Έμ
λ³Έλ¬Έμ λΉμ΄μμ΅λλ€.

## μλ΅(Response) π₯

**μλ²κ° μμ²­μ λν λ΅λ³μ ν΄λΌμ΄μΈνΈ(μ¬μ©μ)μκ² λ³΄λ΄λ κ²**.

### μν μ½λ(Status Code)

μν μ½λμλ μ’λ₯κ° λ§€μ° λ§μ΅λλ€. μν μ½λλ λͺ¨λ μΈ μλ¦¬λ‘ κ΅¬μ±λμ΄ μμΌλ©°,
μλμ κ°μ΄ ν¬κ² λ€μ― λΆλ₯λ‘ λλ©λλ€.

+ **1XX (μ‘°κ±΄λΆ μλ΅)** : μμ²­μ λ°μμΌλ©°, μμμ κ³μ μ§ννλ€.
+ **2XX (μ±κ³΅)** : ν΄λΌμ΄μΈνΈκ° μμ²­ν μμμ μμ λ°κ³  μΉλνμ¬ μ±κ³΅μ μΌλ‘ μ²λ¦¬νμμ μλ¦½λλ€.
+ **3XX (λ¦¬λ€μ΄λ μ μλ£)** : ν΄λΌμ΄μΈνΈλ μμ²­μ μλ£νκΈ° μν΄ μΆκ° λμμ ν΄μΌν©λλ€.
+ **4XX (μμ²­ μλ¬)** : ν΄λΌμ΄μΈνΈκ° λ³΄λΈ μμ²­μ μ€λ₯κ° μλ€λ κ²μ λνλλλ€.
+ **5XX (μλ² μλ¬)** : μλ²κ° ν΄λΌμ΄μΈνΈκ° λ³΄λΈ μμ²­μ μννμ§ λͺ»ν¨μ λνλλλ€.

### μμ²­(Response) HTTP λ©μΈμ§μ μμ

```http
HTTP/1.1 200 OK	// μ±κ³΅μ μλ¦¬λ μνμ½λ
Connection: keep-alive	// ν€λ
Content-Encoding: gzip
Content-Length: 25555
COntent-Type: text/html;

<!DOCTYPE html><html lang="ko" data-reactroot=""><head><script...>
```

#### 1. μλ΅μ μμ(μ²« μ€)

μ²« μ€μ **λ²μ μ μνμ½λμ μνλ©μΈμ§**λ‘ κ΅¬μ±λμ΄ μμ΅λλ€.
**200**μ μ±κ³΅μ μΌλ‘ μμ²­μ λ°μλ€λ μλ―Έμλλ€.

#### 2. ν€λ (λλ²μ§Έ μ€ ~)

λλ²μ§Έ μ€ λΆν°λ ν€λμ΄λ©°, **μλ΅μ λν μ λ³΄**λ₯Ό λ΄κ³  μμ΅λλ€.

#### 3. λ³Έλ¬Έ (~ ν€λμ μ’λ£λ‘λΆν°)

μλ΅μλ λλΆλΆμ μ λ³΄κ° λ³Έλ¬Έμ μμ΅λλ€.
κ·Έ μ΄μ λ λ°μ΄ν°λ₯Ό μμ²­λ°κ³  μλ΅ λ©μΈμ§μλ **μμ²­ν μ λ³΄(λ°μ΄ν°)λ₯Ό λ΄μ
ν΄λΌμ΄μΈνΈμκ² λ³΄λ΄μ£ΌκΈ° λλ¬Έ** μλλ€.
ν΄λΉ μμμλ `HTML`μ λν μ λ³΄κ° λ΄κ²¨μλλ° ν΄λΉ`HTML`λ₯Ό λ°μ
μλ²κ° λΈλΌμ°μ  νλ©΄μ νμλ₯Ό ν©λλ€.

### νκΈ° π

HTTPκ° λ¬΄μμΈμ§ κ³΅λΆν΄λ΄€μ΅λλ€.
μ λ μ½λ©λ³΄λ¨ CSκ³΅λΆκ° λ μ¬λ°λ€μ. π
μλ£κ΅¬μ‘°λ κ³΅λΆν΄μΌνλλ°..
λ€μμ HTTPμμ λ³΄μμ΄ κ°νλ λ²μ μΈ HTTPSλ₯Ό μμλ΄μλ€.


<br>


#### μ°Έκ³  μλ£

+ <a href="https://developer.mozilla.org/ko/docs/Web/HTTP">Mozilla.org</a>
+ <a href="https://velog.io/@surim014/HTTP%EB%9E%80-%EB%AC%B4%EC%97%87%EC%9D%B8%EA%B0%80">@surim014 Velog</a>
+ <a href="https://namu.wiki/w/HTTP">HTTP namu.wiki</a>