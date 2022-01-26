import cv2
import os,sys
is_close=" "

def cal():
    global is_close
    # haarcascade 불러오기
    face_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_frontalface_default.xml')
    eye_cascade = cv2.CascadeClassifier(cv2.data.haarcascades + 'haarcascade_eye.xml')

    # 이미지 불러오기
    img = cv2.imread('sample2.png')
    gray = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)

    # 얼굴 찾기
    faces = face_cascade.detectMultiScale(gray, 1.3, 5)
    for (x, y, w, h) in faces:
        cv2.rectangle(img, (x, y), (x + w, y + h), (255, 0, 0), 2)

        # 눈 찾기
        roi_color = img[y:y + h, x:x + w]
        roi_gray = gray[y:y + h, x:x + w]
        eyes = eye_cascade.detectMultiScale(roi_gray)
        for (ex, ey, ew, eh) in eyes:
            cv2.rectangle(roi_color, (ex, ey), (ex+ew, ey+eh), (0, 255, 0), 2)
    is_close="yes"
    # noti.send_to_firebase_cloud_messaging()
    # os.system("python3 manage.py")
    # 영상 출력
    cv2.imshow('image', img)

    key = cv2.waitKey(0)
    cv2.destroyAllWindows()

# import cv2, dlib

# detector = dlib.get_frontal_face_detector()
# sp = dlib.shape_predictor('shape_predictor_68_face_landmarks.dat')
# cam = cv2.VideoCapture('video.mp4')

# while True:
#     img, frame = cam.read()
#     face = detector(frame)

#     for f in face:
#          #dlib으로 얼굴 검출
#         cv2.rectangle(frame, (f.left(), f.top()), (f.right(), f.bottom()), (0,0,255), 2)
#         land = sp(frame, f)
#         land_list = []
#     for l in land.parts():
#         land_list.append([l.x, l.y])
#         cv2.circle(frame, (l.x, l.y), 3, (255,0,0), -1)