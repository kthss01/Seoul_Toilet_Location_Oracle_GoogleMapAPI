-- 계정 생성 및 권한 주기
CREATE USER GCP_KTH IDENTIFIED BY GCP_KTH;
GRANT RESOURCE, CONNECT TO GCP_KTH;
GRANT CREATE VIEW, SYNONYM TO GCP_KTH;

---------------------------------------------------------------
-- 데이터베이스 분석
-- SEOUL_LOCATION
-- 해당 열 중 필요하다고 생각한 열 정리
-- JDBC에서 편하게 쓰기위해 영어명으로 바꿔서 뷰로 만들 예정

--                                   영어   
--      시도명                    
--      시군구명
--      읍면동명
--      도로명코드 - PK
--      도로명
--      건물명
--      관할행정동
--      X좌표
--      Y좌표

SELECT 건물군여부, 관할행정동 FROM SEOUL_LOCATION WHERE ROWNUM < 100; 
-- 건물군여부 필요 x, 관할행정동 일단 애매해서 놔두기로 함

SELECT 시도명 FROM SEOUL_LOCATION GROUP BY 시도명; -- 시도명은 모두 서울특별시 임, 일단 놔두자
SELECT 시군구명 FROM SEOUL_LOCATION GROUP BY 시군구명 ORDER BY 1; -- 총 25개 성북구, 강북구 등등
SELECT 읍면동명 FROM SEOUL_LOCATION GROUP BY 읍면동명 ORDER BY 1; -- 총 465개

SELECT 도로명 FROM SEOUL_LOCATION GROUP BY 도로명 ORDER BY 1;
SELECT 도로명, COUNT(*) FROM SEOUL_LOCATION GROUP BY 도로명 ORDER BY 1;
SELECT COUNT(도로명) FROM SEOUL_LOCATION; -- 총 541494 개

SELECT 건물명 FROM SEOUL_LOCATION GROUP BY 건물명 ORDER BY 1;
SELECT COUNT(건물명) FROM SEOUL_LOCATION; -- 총 120914 개

SELECT 관할행정동 FROM SEOUL_LOCATION GROUP BY 관할행정동 ORDER BY 1;
SELECT COUNT(관할행정동) FROM SEOUL_LOCATION; -- 총 541217개, 도로명과 유사

SELECT X좌표, Y좌표 FROM SEOUL_LOCATION WHERE ROWNUM < 100; -- 953241.683262 | 1954023.466812 형태

-------------------------------------------------------------
-- SEOUL_TOILET

--                               영어명
--      콘텐츠_ID - PK
--      사용유무
--      콘텐츠명
--      서브카테고리_명
--      구명
--      도로명_주소
--      지번주소
--      좌표_X
--      좌표_Y
--      전화번호
--      상세_제목1
--      상세_내용1
--      상세_제목2
--      상세_내용2
--      상세_제목3
--      상세_내용3
--      상세_제목4
--      상세_내용4
--      상세_제목5
--      상세_내용5

SELECT 사용유무 FROM SEOUL_TOILET GROUP BY 사용유무 ORDER BY 1; -- N, Y N도 있어서 빼면 안됨

------------------
SELECT 콘텐츠명 FROM SEOUL_TOILET GROUP BY 콘텐츠명; -- 화장실 위치라고 보면됨 '위치명 화장실(세부위치)' 또는 '위치명' (위치명의 띄어쓰기 들어가기도함) 이런 형태로 구성
SELECT COUNT(콘텐츠명) FROM SEOUL_TOILET; -- 4585개

------------------
SELECT 서브카테고리_명 FROM SEOUL_TOILET GROUP BY 서브카테고리_명; -- null, 상시, 정시

------------------
SELECT 구명 FROM SEOUL_TOILET GROUP BY 구명; -- null 포함 26개 성북구, 강북구 등

------------------
SELECT 도로명_주소 FROM SEOUL_TOILET GROUP BY 도로명_주소;
SELECT COUNT(도로명_주소) FROM SEOUL_TOILET; -- 4326개

------------------
SELECT 지번주소 FROM SEOUL_TOILET GROUP BY 지번주소;
SELECT COUNT(지번주소) FROM SEOUL_TOILET; -- 4583개 왜 도로명보다 많은진 모르겠음

------------------
SELECT 좌표_X, 좌표_Y FROM SEOUL_TOILET; -- 127.028265783 | 37.620154692 형태

------------------
SELECT 전화번호 FROM SEOUL_TOILET WHERE ROWNUM < 100;

SELECT COUNT(전화번호) FROM SEOUL_TOILET; -- 4527개

SELECT COUNT(전화번호) FROM SEOUL_TOILET WHERE 전화번호 LIKE '02-%'; -- 4146 02로 시작하는 전화번호
SELECT 전화번호 FROM SEOUL_TOILET WHERE 전화번호 LIKE '02-%';

SELECT COUNT(전화번호) FROM SEOUL_TOILET WHERE 전화번호 NOT LIKE '02-%'; -- 381 02로 시작하지 않는 전화번호 -> 수정 필요
SELECT 전화번호 FROM SEOUL_TOILET WHERE 전화번호 NOT LIKE '02-%';

SELECT * FROM SEOUL_TOILET WHERE 전화번호 IS NULL;
SELECT COUNT(*) FROM SEOUL_TOILET WHERE 전화번호 IS NULL; -- 전화번호 없는게 58개

------------------
SELECT 상세_제목1 FROM SEOUL_TOILET GROUP BY 상세_제목1; -- 상세위치, null, 유형, 시설구분
SELECT COUNT(상세_제목1) FROM SEOUL_TOILET WHERE 상세_제목1 = '시설구분'; -- 64개
SELECT 상세_내용1 FROM SEOUL_TOILET WHERE 상세_제목1 = '시설구분' GROUP BY 상세_내용1;
SELECT 상세_내용1 FROM SEOUL_TOILET WHERE 상세_제목1 = '상세위치'; -- 3개, 쌍한교 옆, 강북소방서 건너편, 우이천변 초안교 옆, 강북구민운동장사거리 측

SELECT 상세_내용1 FROM SEOUL_TOILET WHERE 상세_제목1 = '유형';
SELECT 상세_내용1 FROM SEOUL_TOILET WHERE 상세_제목1 = '유형' GROUP BY 상세_내용1; -- 11개, 민간개방공중화장실, 개방시간1,2, 민간개방, 공중, 민간개방1, 무인공중, 개방시간3, 공공개방, 공원, 공중화장실
SELECT 상세_내용1, COUNT(*) FROM SEOUL_TOILET WHERE 상세_제목1 = '유형' GROUP BY 상세_내용1;

SELECT * FROM SEOUL_TOILET WHERE 상세_내용1 = '민간개방공중화장실'; -- 1개
UPDATE SEOUL_TOILET SET 상세_내용1 = '민간개방' WHERE 상세_내용1 = '민간개방공중화장실';

SELECT * FROM SEOUL_TOILET WHERE 상세_내용1 = '공중'; -- 1594개
UPDATE SEOUL_TOILET SET 상세_내용1 = '공공개방' WHERE 상세_내용1 = '공중';

SELECT * FROM SEOUL_TOILET WHERE 상세_내용1 = '무인공중'; -- 10개
UPDATE SEOUL_TOILET SET 상세_내용1 = '공공개방' WHERE 상세_내용1 = '무인공중';

SELECT * FROM SEOUL_TOILET WHERE 상세_내용1 = '민간개방'; -- 283개
SELECT * FROM SEOUL_TOILET WHERE 상세_내용1 = '민간개방1'; -- 664개
UPDATE SEOUL_TOILET SET 상세_내용1 = '민간개방' WHERE 상세_내용1 = '민간개방1';

SELECT * FROM SEOUL_TOILET WHERE 상세_내용1 = '공공개방'; -- 1950개

SELECT * FROM SEOUL_TOILET WHERE 상세_제목1 = '유형' AND 상세_내용1 = '공원'; -- 4개 -> 상세제목1 시설구분으로 가야함
UPDATE SEOUL_TOILET SET 상세_제목1 = '시설구분' WHERE 상세_제목1 = '유형' AND 상세_내용1 = '공원';
SELECT * FROM SEOUL_TOILET WHERE 상세_제목1 = '유형' AND 상세_내용1 = '공중화장실'; -- 2개 -> 상세제목1 시설구분으로 가야함
UPDATE SEOUL_TOILET SET 상세_제목1 = '시설구분' WHERE 상세_제목1 = '유형' AND 상세_내용1 = '공중화장실';

COMMIT;

SELECT 상세_내용1 FROM SEOUL_TOILET WHERE 상세_제목1 = '시설구분' GROUP BY 상세_내용1; -- 기타, 공공기관, 병원, 개방화장실, 공중화장실, 공원

-- 유형에서 개방시간 부분은 2파트로 넘겨야한다
SELECT 상세_내용1 FROM SEOUL_TOILET WHERE 상세_제목1 = '유형' GROUP BY 상세_내용1; -- 3개, 개방시간1,2,3, 민간개방, 공공개방,
SELECT * FROM SEOUL_TOILET WHERE 상세_제목1 = '유형' AND 상세_내용1 NOT IN ('민간개방', '공공개방');

------------------
SELECT 상세_제목2 FROM SEOUL_TOILET GROUP BY 상세_제목2; -- null, 개방시간, 시설구분
SELECT COUNT(상세_제목2) FROM SEOUL_TOILET WHERE 상세_제목2 = '시설구분'; -- 3개 상세_제목1로 옮겨야함

SELECT * FROM SEOUL_TOILET WHERE 상세_제목2 = '시설구분';

SELECT 상세_내용2 FROM SEOUL_TOILET WHERE 상세_제목2 = '개방시간' GROUP BY 상세_내용2; -- 366개, 정시,상시,평일이 포함되기도 함 null, (시간)이렇게 되어잇기도 함
SELECT 상세_내용2 FROM SEOUL_TOILET WHERE 상세_제목2 = '시설구분' GROUP BY 상세_내용2; -- 공중화장실

------------------
SELECT 상세_제목3 FROM SEOUL_TOILET GROUP BY 상세_제목3; -- null, 개방시간, 화장실현황, 소재지 용도, 소재지용도, 화장실 현황, 변기현황 -> 정리 필요
SELECT 상세_내용3 FROM SEOUL_TOILET WHERE 상세_제목3 = '개방시간' GROUP BY 상세_내용3; -- 1개 24시간 상세_제목2로 옮겨야할듯 -> 이것도 상세위치 때문에 밀린거 놔두자
SELECT * FROM SEOUL_TOILET WHERE 상세_제목3 = '개방시간';

SELECT 상세_내용3 FROM SEOUL_TOILET WHERE 상세_제목3 = '화장실현황' GROUP BY 상세_내용3; -- 16개 남자: 대변기1 소변기2 /여자: 대변기1 / 장애인화장실(공용) 이런 형태
UPDATE SEOUL_TOILET SET 상세_제목3 = '화장실현황' WHERE 상세_제목3 = '화장실 현황' OR 상세_제목3 = '변기현황';
SELECT 상세_내용3 FROM SEOUL_TOILET WHERE 상세_제목3 = '화장실 현황' GROUP BY 상세_내용3; -- 19개 위와 같은 형태
SELECT 상세_내용3 FROM SEOUL_TOILET WHERE 상세_제목3 = '변기현황' GROUP BY 상세_내용3; -- 18개 위와 같은 형태

SELECT 상세_내용3 FROM SEOUL_TOILET WHERE 상세_제목3 = '소재지 용도' GROUP BY 상세_내용3; -- 35개
SELECT 상세_내용3 FROM SEOUL_TOILET WHERE 상세_제목3 = '소재지용도' GROUP BY 상세_내용3; -- 20개

------------------
SELECT 상세_제목4 FROM SEOUL_TOILET GROUP BY 상세_제목4; -- 10개, 기타설비, 장애인화장실유무, null, 장애인화장실, 장애인화장실 유무, 기타시설, 장애인시설유무, 변기현황, 편의시설, 장애인시설 -> 정리 필요

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '기타설비' GROUP BY 상세_내용4; -- 36개, 비상벨, CCTV 같은 얘기
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '기타시설' GROUP BY 상세_내용4; -- 3개 null, 위와 비슷 합쳐야함

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인화장실유무' GROUP BY 상세_내용4; -- null
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인화장실' GROUP BY 상세_내용4; -- 129개, 남 x개, 여x개 장애인 or 어린이 여부도 있음
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인화장실 유무' GROUP BY 상세_내용4; -- null
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인시설유무' GROUP BY 상세_내용4; -- null
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인시설' GROUP BY 상세_내용4; -- null

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '변기현황' GROUP BY 상세_내용4; -- 3개 상세_내용3으로 옮겨야함
]
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '편의시설' GROUP BY 상세_내용4; -- 3개 null, 편의시설, 손건조기, 상세내용 5와 합쳐야함


------------------
SELECT 상세_제목5 FROM SEOUL_TOILET GROUP BY 상세_제목5; -- 7개, null, 기타설비, 편의시설 유무, 편의시설 설치여부, 비고, 기타시설, 편의시설 -> 정리 필요

SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '기타설비' GROUP BY 상세_내용5; -- 2개, 비상벨 설치 여부, 상세내용4와 합쳐야함
SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '기타시설' GROUP BY 상세_내용5; -- 비상벨 설치 여부 상세내용4와 합쳐야함

SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '편의시설 유무' GROUP BY 상세_내용5; -- null
SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '편의시설 설치여부' GROUP BY 상세_내용5; -- null
SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '편의시설' GROUP BY 상세_내용5; -- 348개, 빈값도 있음, 기저귀교환대4유아용보호의자2비상벨6손건조기6 이런 형태

SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '비고' GROUP BY 상세_내용5; -- 2개, 1층 장애인화장실 / 2층 일반화장실, 산지형 공원

------------------