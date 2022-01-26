import base64
from http import server
import cv2
import requests
import json
import datetime
from requests.api import head


if __name__ == "__main__":
    id = "beomsic"
    name = "Beomseok"
   
   
    # 현재 시간 파악.
    current_date = datetime.datetime.now()
    yesterday=current_date-datetime.timedelta(days=1)
    tomorrow=current_date+datetime.timedelta(days=1)
    nowDate = current_date.strftime('%Y-%m-%d')
    yesterdayDate=yesterday.strftime('%Y-%m-%d')
    tomorrowDate=tomorrow.strftime('%Y-%m-%d')
    # nowTime = current_date.strftime('%H:%M:%S')
    token="";
    # JSON 형식으로 서버에 전달
    headers = {'Content-Type': 'application/json; charset=utf-8'}
    res = requests.post("http://localhost:8080/data",
                        json={
                            'id': id,
                            'nowDate': nowDate,
                            'token':token,
                            'isClose':"yes"
                        },
                        headers=headers)



