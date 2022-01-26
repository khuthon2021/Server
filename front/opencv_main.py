from opencv_turtle import *

def start_opencv():
    POSE_PAIRS_BODY_25 = [
        [0,1], # nose - neck
        [1,18], # neck - LEar
        [1,17] # neck - REar
    ]

    # 신경 네트워크의 구조를 지정하는 prototxt 파일 (다양한 계층이 배열되는 방법 등)
    protoFile_body_25 = "./pose_deploy.prototxt"

    # 훈련된 모델의 weight 를 저장하는 caffemodel 파일
    weightsFile_body_25 = "./pose_iter_584000.caffemodel"

    # 이미지 경로 (출처: https://news.nate.com/view/20190906n05074?mid=n0400)
    path_list = []
    turtleneck = "./image_copy.jpg"
    normalneck = "../raspberry/normal.jpeg"
    # path_list.extend([turtleneck, normalneck])
    path_list.extend([turtleneck])

    # 키포인트를 저장할 빈 리스트
    points = []

    for path in path_list:
        frame_man = output_keypoints(image_path=path, proto_file=protoFile_body_25, weights_file=weightsFile_body_25,
                                     threshold=0.1, model_name=path.split('/')[-1], BODY_PARTS=BODY_PARTS_BODY_25)
        output_keypoints_with_lines(POSE_PAIRS=POSE_PAIRS_BODY_25, frame=frame_man)


# if __name__ == "__main__":
    
#     POSE_PAIRS_BODY_25 = [
#         [0,1], # nose - neck
#         [1,18], # neck - LEar
#         [1,17] # neck - REar
#     ]

#     # 신경 네트워크의 구조를 지정하는 prototxt 파일 (다양한 계층이 배열되는 방법 등)
#     protoFile_body_25 = "./pose_deploy.prototxt"

#     # 훈련된 모델의 weight 를 저장하는 caffemodel 파일
#     weightsFile_body_25 = "./pose_iter_584000.caffemodel"

#     # 이미지 경로 (출처: https://news.nate.com/view/20190906n05074?mid=n0400)
#     path_list = []
#     turtleneck = "./image_copy.jpg"
#     normalneck = "../raspberry/normal.jpeg"
#     path_list.extend([turtleneck, normalneck])

#     # 키포인트를 저장할 빈 리스트
#     points = []

#     for path in path_list:
#         frame_man = output_keypoints(image_path=path, proto_file=protoFile_body_25, weights_file=weightsFile_body_25,
#                                      threshold=0.1, model_name=path.split('/')[-1], BODY_PARTS=BODY_PARTS_BODY_25)
#         output_keypoints_with_lines(POSE_PAIRS=POSE_PAIRS_BODY_25, frame=frame_man)