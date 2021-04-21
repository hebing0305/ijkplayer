package com.kjsc.ijkplayer;

public class ErrorCode {
    static final int MEDIA_INFO_UNKNOWN = 1;//未知信息
    static final int MEDIA_INFO_STARTED_AS_NEXT = 2;//播放下一条
    static final int MEDIA_INFO_VIDEO_RENDERING_START = 3;//视频开始整备中，准备渲染
    static final int MEDIA_INFO_VIDEO_TRACK_LAGGING = 700;//视频日志跟踪
    static final int MEDIA_INFO_BUFFERING_START = 701;//开始缓冲中 开始缓冲
    static final int MEDIA_INFO_BUFFERING_END = 702;//缓冲结束
    static final int MEDIA_INFO_NETWORK_BANDWIDTH = 703;//网络带宽，网速方面
    static final int MEDIA_INFO_BAD_INTERLEAVING = 800;//
    static final int MEDIA_INFO_NOT_SEEKABLE = 801;//不可设置播放位置，直播方面
    static final int MEDIA_INFO_METADATA_UPDATE = 802;//
    static final int MEDIA_INFO_TIMED_TEXT_ERROR = 900;
    static final int MEDIA_INFO_UNSUPPORTED_SUBTITLE = 901;//不支持字幕
    static final int MEDIA_INFO_SUBTITLE_TIMED_OUT = 902;//字幕超时
    static final int MEDIA_INFO_VIDEO_INTERRUPT = -10000;//数据连接中断，一般是视频源有问题或者数据格式不支持，比如音频不是AAC之类的
    static final int MEDIA_INFO_VIDEO_ROTATION_CHANGED = 10001;//视频方向改变，视频选择信息
    static final int MEDIA_INFO_AUDIO_RENDERING_START = 10002;//音频开始整备中
    static final int MEDIA_ERROR_SERVER_DIED = 100;//服务挂掉，视频中断，一般是视频源异常或者不支持的视频类型。
    static final int MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200;//数据错误没有有效的回收
    static final int MEDIA_ERROR_IO = -1004;//IO 错误
    static final int MEDIA_ERROR_MALFORMED = -1007;
    static final int MEDIA_ERROR_UNSUPPORTED = -1010;//数据不支持
    static final int MEDIA_ERROR_TIMED_OUT = -110;//数据超时
}
