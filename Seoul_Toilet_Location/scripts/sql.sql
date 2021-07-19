-- 계정 생성 및 권한 주기
CREATE USER GCP_KTH IDENTIFIED BY GCP_KTH;
GRANT RESOURCE, CONNECT TO GCP_KTH;
GRANT CREATE VIEW, SYNONYM TO GCP_KTH;

---------------------------------------------------------------
-- 데이터베이스 분석
-- SEOUL_LOCATION
-- 해당 열 중 필요하다고 생각한 열 정리
-- JDBC에서 편하게 쓰기위해 영어명으로 바꿔서 뷰로 만들 예정

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
UPDATE SEOUL_TOILET SET 상세_제목3 = '소재지용도' WHERE 상세_제목3 = '소재지 용도';

COMMIT;
------------------
SELECT 상세_제목4 FROM SEOUL_TOILET GROUP BY 상세_제목4; -- 10개, 기타설비, 장애인화장실유무, null, 장애인화장실, 장애인화장실 유무, 기타시설, 장애인시설유무, 변기현황, 편의시설, 장애인시설 -> 정리 필요

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '기타설비' GROUP BY 상세_내용4; -- 36개, 비상벨, CCTV 같은 얘기
UPDATE SEOUL_TOILET SET 상세_제목4 = '기타설비' WHERE 상세_제목4 = '기타시설';
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '기타시설' GROUP BY 상세_내용4; -- 3개 null, 위와 비슷 합쳐야함

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인화장실유무' GROUP BY 상세_내용4; -- null
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인화장실' GROUP BY 상세_내용4; -- 129개, 남 x개, 여x개 장애인 or 어린이 여부도 있음
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인화장실 유무' GROUP BY 상세_내용4; -- null
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인시설유무' GROUP BY 상세_내용4; -- null
SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '장애인시설' GROUP BY 상세_내용4; -- null
UPDATE SEOUL_TOILET SET 상세_제목4 = '장애인화장실' WHERE 상세_제목4 IN('장애인화장실유무', '장애인화장실 유무', '장애인시설유무', '장애인시설');

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '변기현황' GROUP BY 상세_내용4; -- 3개 상세_내용3으로 옮겨야함
UPDATE SEOUL_TOILET SET 상세_제목4 = '화장실현황' WHERE 상세_제목4 = '변기현황';

SELECT 상세_내용4 FROM SEOUL_TOILET WHERE 상세_제목4 = '편의시설' GROUP BY 상세_내용4; -- 3개 null, 편의시설, 손건조기, 상세내용 5와 합쳐야함

COMMIT;

------------------
SELECT 상세_제목5 FROM SEOUL_TOILET GROUP BY 상세_제목5; -- 7개, null, 기타설비, 편의시설 유무, 편의시설 설치여부, 비고, 기타시설, 편의시설 -> 정리 필요

SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '기타설비' GROUP BY 상세_내용5; -- 2개, 비상벨 설치 여부, 상세내용4와 합쳐야함
SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '기타시설' GROUP BY 상세_내용5; -- 비상벨 설치 여부 상세내용4와 합쳐야함
UPDATE SEOUL_TOILET SET 상세_제목5 = '기타설비' WHERE 상세_제목5 = '기타시설';


SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '편의시설 유무' GROUP BY 상세_내용5; -- null
SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '편의시설 설치여부' GROUP BY 상세_내용5; -- null
SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '편의시설' GROUP BY 상세_내용5; -- 348개, 빈값도 있음, 기저귀교환대4유아용보호의자2비상벨6손건조기6 이런 형태
UPDATE SEOUL_TOILET SET 상세_제목5 = '편의시설' WHERE 상세_제목5 IN ('편의시설 유무', '편의시설 설치여부');

SELECT 상세_내용5 FROM SEOUL_TOILET WHERE 상세_제목5 = '비고' GROUP BY 상세_내용5; -- 2개, 1층 장애인화장실 / 2층 일반화장실, 산지형 공원

COMMIT;

------------------

-- 해당 위치에 가까운 화장실 판별하기 위해 구별로 햇을 때 몇개의 화장실이 나오는지 확인
SELECT 구명, COUNT(*) FROM SEOUL_TOILET GROUP BY 구명;
SELECT * FROM SEOUL_TOILET WHERE 구명 IS NULL; -- NULL 처리 필요할듯 14개 1는 구로구 나머진 강남구로 변경하면 됨

UPDATE SEOUL_TOILET SET 구명 = '구로구' WHERE 콘텐츠_ID = 'restgu17_0119';
UPDATE SEOUL_TOILET SET 구명 = '강남구' WHERE 구명 IS NULL;

COMMIT;

SELECT * FROM SEOUL_TOILET WHERE 사용유무 IS NULL;
SELECT * FROM SEOUL_TOILET WHERE 콘텐츠명 IS NULL;

SELECT * FROM SEOUL_TOILET WHERE 서브카테고리_명 IS NULL; -- NULL 처리 필요 상시 or 정시로. 54개
SELECT 서브카테고리_명 FROM SEOUL_TOILET GROUP BY 서브카테고리_명;
-- NULL 값 중 상시 처리 조건 - 상세_내용2 기준 상시, 24시간, 24시
-- 정시 처리 조건 - 나머지
UPDATE SEOUL_TOILET SET 서브카테고리_명 = '상시' WHERE 서브카테고리_명 IS NULL AND 상세_내용2 IN ('상시', '24시간', '24시');
UPDATE SEOUL_TOILET SET 서브카테고리_명 = '정시' WHERE 서브카테고리_명 IS NULL AND 상세_내용2 NOT IN ('상시', '24시간', '24시');
UPDATE SEOUL_TOILET SET 서브카테고리_명 = '정시' WHERE 서브카테고리_명 IS NULL AND 상세_내용2 IS NULL;

COMMIT;
ROLLBACK;

SELECT * FROM SEOUL_TOILET WHERE 구명 IS NULL;

SELECT * FROM SEOUL_TOILET WHERE 도로명_주소 IS NULL;
SELECT COUNT(*) FROM SEOUL_TOILET WHERE 도로명_주소 IS NULL; -- 259개
-- 이건 필요하진 않을거 같아 처리 x

-- 도로명으로 구분할 땐 어떻게 되는지 확인 앞에 도로명 길이가 애매하고 NULL도 있어서 이 방법으론 못 나눌듯)

-- 물리모델링 - 컬럼명 영어로 해서 뷰 정도만 만들자

-- SEOUL_LOCATION               SEOUL_VIEW
--                                   영어   
--      시도명                    
--      시군구명
--      읍면동명
--      도로명코드 - PK -> PK로 못씀
--      도로명
--      건물본번        - 이 2개도 필요함
--      건물부번     
--      건물명
--      관할행정동
--      X좌표
--      Y좌표

CREATE OR REPLACE VIEW SEOUL_VIEW
    (ROAD_CODE, SI_DO_NAME, SI_GUN_GU_NAME, EUP_MYEON_DONG_NAME, ROAD_ADDRESS, BUILDING_MAIN_NUM, BUILDING_SUB_NUM, BUILDING_NAME, HANG_JUNG_DONG_NAME, LOC_X, LOC_Y)
AS 
    SELECT
        도로명코드, 시도명, 시군구명, 읍면동명, 도로명, 건물본번, 건물부번, 건물명, 관할행정동, X좌표, Y좌표
    FROM SEOUL_LOCATION;
    
SELECT * FROM SEOUL_VIEW WHERE ROWNUM < 100;

-- 테스트
SELECT * FROM SEOUL_VIEW WHERE BUILDING_NAME LIKE '서울역%';
SELECT * FROM SEOUL_VIEW WHERE BUILDING_NAME LIKE '강남역%';
SELECT * FROM SEOUL_VIEW WHERE BUILDING_NAME LIKE '%교보%';

SELECT * FROM SEOUL_VIEW WHERE ROAD_NAME = '종로' AND BUILDING_NAME = '교보생명빌딩';
SELECT * FROM SEOUL_VIEW WHERE ROAD_NAME = '장충단로13길';
SELECT * FROM SEOUL_VIEW WHERE ROAD_NAME = '테헤란로14길' AND BUILDING_MAIN_NUM = '6';

---------------------------------------------
--        SEOUL_TOILET         TOILET_VIEW
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

CREATE OR REPLACE VIEW TOILET_VIEW
    (TOILET_ID, USEABLE, LOCATION_NAME, USING_TIME, GU_NAME, ROAD_ADDRESS, NUM_ADDRESS, LOC_X, LOC_Y, PHONE, 
    DETAIL_NAME1, DETAIL_CONTENT1, DETAIL_NAME2, DETAIL_CONTENT2, DETAIL_NAME3, DETAIL_CONTENT3, DETAIL_NAME4, DETAIL_CONTENT4, DETAIL_NAME5, DETAIL_CONTENT5)
AS
    SELECT 
        콘텐츠_ID, 사용유무, 콘텐츠명, 서브카테고리_명, 구명, 도로명_주소, 지번주소, 좌표_X, 좌표_Y, 전화번호,
        상세_제목1, 상세_내용1, 상세_제목2, 상세_내용2, 상세_제목3, 상세_내용3, 상세_제목4, 상세_내용4, 상세_제목5, 상세_내용5
    FROM SEOUL_TOILET;
    
SELECT * FROM TOILET_VIEW WHERE ROWNUM < 100;
    
-- query
SELECT ROAD_ADDRESS, BUILDING_MAIN_NUM, BUILDING_SUB_NUM, BUILDING_NAME, LOC_X, LOC_Y FROM SEOUL_VIEW;

SELECT ROAD_ADDRESS, BUILDING_MAIN_NUM, BUILDING_SUB_NUM, BUILDING_NAME, LOC_X, LOC_Y FROM SEOUL_VIEW WHERE LOC_X IS NULL OR LOC_Y IS NULL;
SELECT COUNT(*) FROM SEOUL_VIEW WHERE LOC_X IS NULL OR LOC_Y IS NULL; -- 454개
SELECT ROAD_ADDRESS, BUILDING_MAIN_NUM, BUILDING_SUB_NUM, LOC_X, LOC_Y FROM SEOUL_VIEW WHERE ROAD_ADDRESS IS NULL OR BUILDING_MAIN_NUM IS NULL OR BUILDING_SUB_NUM IS NULL;

SELECT COUNT(*) FROM TOILET_VIEW;

UPDATE SEOUL_VIEW SET LOC_X = 0, LOC_Y = 0 WHERE ROAD_ADDRESS = '자하문로' AND BUILDING_MAIN_NUM = 94 AND BUILDING_SUB_NUM = 0; 
ROLLBACK;

-- 화장실 전체 조회
SELECT COUNT(*) FROM TOILET_VIEW;
SELECT * FROM TOILET_VIEW;

-- 도로명 주소, 건물본번, 건물부번 입력받아 서울 위치에서 가져오기
SELECT * FROM SEOUL_VIEW WHERE ROAD_ADDRESS = '자하문로' AND BUILDING_MAIN_NUM = 94 AND BUILDING_SUB_NUM = 0;
SELECT SI_GUN_GU_NAME, ROAD_ADDRESS, BUILDING_MAIN_NUM, BUILDING_SUB_NUM, BUILDING_NAME, HANG_JUNG_DONG_NAME, LOC_X, LOC_Y FROM SEOUL_VIEW WHERE ROAD_ADDRESS = '자하문로' AND BUILDING_MAIN_NUM = 94 AND BUILDING_SUB_NUM = 0;

-- 해당 좌표 958370.1615536913, 1957961.3499799788 와 가장 가까운 위치 가져오기
SELECT * FROM SEOUL_VIEW WHERE ABS(LOC_X - 958370.1615536913) + ABS(LOC_Y - 1957961.3499799788) = (SELECT MIN(ABS(LOC_X - 958370.1615536913) + ABS(LOC_Y - 1957961.3499799788)) FROM SEOUL_VIEW);
SELECT SI_GUN_GU_NAME, ROAD_ADDRESS, BUILDING_MAIN_NUM, BUILDING_SUB_NUM, BUILDING_NAME, HANG_JUNG_DONG_NAME, LOC_X, LOC_Y FROM SEOUL_VIEW WHERE ABS(LOC_X - 958370.1615536913) + ABS(LOC_Y - 1957961.3499799788) = (SELECT MIN(ABS(LOC_X - 958370.1615536913) + ABS(LOC_Y - 1957961.3499799788)) FROM SEOUL_VIEW);

-- WGS84 거리 계산 함수 만들어보기
CREATE OR REPLACE FUNCTION RADIANS(nDegrees IN NUMBER) 
RETURN NUMBER DETERMINISTIC 
IS
BEGIN
  /*
  -- radians = degrees / (180 / pi)
  -- RETURN nDegrees / (180 / ACOS(-1)); but 180/pi is a constant, so...
  */
  RETURN nDegrees / 57.29577951308232087679815481410517033235;
END RADIANS;
/
 
CREATE OR REPLACE FUNCTION DISTANCE_WGS84( H_LAT IN NUMBER, H_LNG IN NUMBER, T_LAT IN NUMBER, T_LNG IN NUMBER)
RETURN NUMBER DETERMINISTIC
IS
BEGIN
  RETURN ( 6371.0 * acos(  
          cos( RADIANS( H_LAT ) ) * cos( RADIANS( T_LAT /* 위도 */ ) )
          *cos( RADIANS( T_LNG /* 경도 */ ) - RADIANS( H_LNG ) )
          +
          sin( RADIANS( H_LAT ) ) * sin( RADIANS( T_LAT /* 위도 */ ) )        
         ));
END DISTANCE_WGS84;
/

select DISTANCE_WGS84(33.504274, 126.529182, 33.524383, 126.544333) from dual;
/* 결과 2.64059773979495999846417249534463003211 */
/*
  삼성혈 LOCALY = 33.504274, LOCALX = 126.529182
*/

select DISTANCE_WGS84(37.620154692, 127.028265783, 37.637437605, 127.027308394) from dual;

-- 37.57094330575503, 126.99790973487023 이 거리 중 가장 가까운 값 15개 찾기
SELECT DISTANCE_WGS84(37.57094330575503, 126.99790973487023, LOC_Y, LOC_X), TOILET_VIEW.* FROM TOILET_VIEW ORDER BY 1 ASC;

SELECT * FROM (SELECT ROUND(DISTANCE_WGS84(37.57094330575503, 126.99790973487023, LOC_Y, LOC_X), 4) DISTANCE, TOILET_VIEW.* FROM TOILET_VIEW ORDER BY 1 ASC) WHERE ROWNUM <= 15;

SELECT * FROM (SELECT ROUND(DISTANCE_WGS84(37.56705301262327, 126.9664894134985, LOC_Y, LOC_X), 4) DISTANCE, TOILET_VIEW.* FROM TOILET_VIEW ORDER BY 1 ASC) WHERE ROWNUM <= 15;
