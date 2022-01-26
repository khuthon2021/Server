from cv2 import pencilSketch
from flask import Flask, request, jsonify, Response
import json
# import opencv_main
import picture
import os

app = Flask(__name__)


@app.route('/')
def hello_world():
    return "hello world"


@app.route('/opencv')
def getOpencv():
    return "hello world"


@app.route('/opencv', methods=['POST'])
def opencv():
    # POST로 전달 받은 JSON 형식 data get
    # data = request.get_json()

    # make image file
    # photo_decoding.decode_image(data['encodingContent'])

  
    picture.cal()
    is_close = picture.is_close
    print("is_close : ", is_close)
    # 사진 사용 후 사진 삭제
    # os.remove("image_copy.jpg")

    # result = {'id': "1", 'user_name': user_name, 'now_Date': now_Date,
    #         'now_Time': now_Time, 'is_turtle': is_Turtle}
    result = {'is_close': is_close}
    # JSON 형태로 return
    response = app.response_class(
        response=json.dumps(result),
        status=200,
        mimetype='application/json'
    )
    return response


if __name__ == "__main__":
    app.run()
