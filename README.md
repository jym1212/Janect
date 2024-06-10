# Bumblebee
Open Source Project

범블비 팀의 자넥트 (Janect) 프로젝트
개요
자넥트는 범블비 팀이 개발한 혁신적인 모바일 애플리케이션입니다. 다음과 같은 주요 구성 요소를 포함하여 사용자에게 다채로운 기능을 제공합니다.

# 시연 예제

<img width="233" alt="Ph20240529123403 001" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/4c8f5168-23c9-4226-af8e-d8a09bee5aae">
<img width="217" alt="Ph20240529123348" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/314a2d99-531e-4271-aa52-031e86cf7006">
<img width="192" alt="Ph20240529123344 001" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/8d7b2d6b-2d57-40a5-a0fa-f1440df50b6a">
<img width="195" alt="Ph20240529123318" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/f494a781-b47b-4a08-8e59-244112dc4bb5">
<img width="195" alt="Ph20240529123304" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/89948236-8b17-43d5-90dd-bf6b5ba8797c">
<img width="244" alt="Ph20240529123245 002" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/c2c60f4c-c0f7-4a7a-b7c5-ad2fa433ecdd">
<img width="243" alt="Ph20240529123245 001" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/5518fa49-ee95-4bb7-bfe7-2c56694bcd09">
<img width="244" alt="Ph20240529122926" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/354b5db7-85cf-4c4a-b2af-79eb198b9618">
<img width="235" alt="Ph20240529122906 002" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/5041fbfd-97be-4f0d-8a58-be4d27530748">
<img width="243" alt="Ph20240529122906 001" src="https://github.com/Heisnotanimposter/Bumblebee/assets/97718938/019c0af6-04d5-4944-8347-b92ef9349ed5">

자넥트 (Janect): 핵심 애플리케이션 (C:\Users\redca\Documents\Bumblebee\Bumblebee\MyApplication)
app: 애플리케이션 소스 코드 및 리소스 (C:\Users\redca\Documents\Bumblebee\Bumblebee\MyApplication\app)
gradle: Gradle 빌드 시스템 관련 파일 (C:\Users\redca\Documents\Bumblebee\Bumblebee\MyApplication\gradle)
SLM (Statistical Language Model): 자연어 처리 및 텍스트 생성 기능 담당
API: 외부 시스템과의 인터페이스 제공 (C:\Users\redca\Documents\Bumblebee\SLM\API)
dataset: 시스템 학습 및 운영에 필요한 데이터셋 (C:\Users\redca\Documents\Bumblebee\SLM\dataset)
GPT2main: GPT-2 기반 모델 관련 파일 (C:\Users\redca\Documents\Bumblebee\SLM\GPT2main)
+ Autocomplete.tflite
+ LLM Inference <- not work
+ MLCChat Interface <- Need Rust, Complicate

# Dataset
https://catalog.data.gov/dataset/cpa-search

https://www.data.go.kr/iim/api/selectAPIAcountView.do <br>
https://www.data.go.kr/data/15074415/openapi.do<br>
https://www.data.go.kr/data/15003024/openapi.do<br>
https://www.data.go.kr/data/15003029/openapi.do<br>


# route
./Bumblebee/dataset/ ..

# usage

일반 인증키
(Encoding)	
iBnhEtxdvRgWB11T%2FLDEYLkaJ4HMmCecarGXtMain7K20fr%2F7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA%3D%3D
일반 인증키
(Decoding)	
iBnhEtxdvRgWB11T/LDEYLkaJ4HMmCecarGXtMain7K20fr/7JhIxc1Q6VYvXE9SzOGXjcCYgr22eBhvAxxtxA==





#개발 환경 설정

Android Studio 설치: 자넥트 앱 개발 및 빌드 환경
Python 설치: SLM 모델 개발 및 실행 환경
딥러닝 프레임워크 설치: (예: TensorFlowLite, google-Mediapipe, MLCChat) SLM 모델 학습 및 추론
Gradle 빌드 설정: Android Studio에서 프로젝트 가져오기 후 자동 설정
데이터셋 준비: SLM 모델 학습 및 평가를 위한 데이터셋 확보
API Key 발급: 외부 서비스 연동을 위한 API Key 발급 및 설정
실행 방법
자넥트 (Janect): Android Studio에서 프로젝트 열고 빌드 및 실행
SLM: Python 환경에서 GPT2main 스크립트 실행
API 연동: API 엔드포인트 및 요청/응답 형식에 따라 외부 시스템과 연동

