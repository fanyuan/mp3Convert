# MyMp3Convert
mp3Convert
一个mp3转换库，基于lame3来实现

模块简介：
================================================
主要实现了wav格式向mp3格式的转投；  
只需要关注3个方法就好；  
为了项目简单直观实用，节省代码空间，本模块遵循单一职责原则，只做格式转换，把wav格式音频转换成mp3格式

使用场景:
================================================
 用于在客户端需要把编辑好的wav转换成mp3格式，这样可以在不过大损失音频效果的同时有效节省空间，通常情况下，10M大小的wav文件通过转换后可以生成1M大小的mp3文件；  
 比如：用户在客户端录制好pcm音频文件，并由该文件编辑得到了可播放的wav文件，又需要把wav文件上传服务器，或者存在本地，但是wav文件过大，需要占用过大网络，在增加耗电量的同时，也给了服务器带来了压力，这个时候可以把文件转换成mp3格式文件（至于pcm到wav格式的转换，wav文件的编辑、剪切、拼接、混音合成可以使用另一模块来实现 链接：https://github.com/fanyuan/AudioUtil ）


 使用：
 ================================================
只需要调用Mp3ConvertUtilHelper里的3个公开方法即可；   
使用很简单，只要把本module下载后以Android studio导入工程项目，设为library估其他module依赖就好  
对于和native c语言的交互已在Mp3ConvertUtil中封装好，采用了外观模式；  
使用时只需要调用Mp3ConvertUtilHelper类的相3个关公开方法  

=====================华丽丽的分割线========================

3个相关方法如下：
================================================

    /**
     * wav转换成mp3
     * @param wavInPath    需要转换的wav源文件输入路径
     * @param mp3OutPath   转换完成后的mp3目标文件输出路径
     */
    public static void convertmp3(String wavInPath, String mp3OutPath)

    /**
     * wav转换成mp3
     * @param wavInPath    需要转换的wav源文件输入路径
     * @param mp3OutPath   转换完成后的mp3目标文件输出路径
     * @param callback     转换相关的回调
     */
    public static void convertmp3(String wavInPath, String mp3OutPath, Mp3ConvertUtil.ConvertListener callback)}

    /**
     * 获取转换进度
     * @param outPath  在多个任务并行时以输出路径为token来查询相关转换文件的转换进度
     * @return
     */
    public static int getProgress(String outPath)

 =====================华丽丽的分割线==========================
 
使用示例如下：  

温馨提示：使用时请在子线程中使用，为了演示方便直观就不过多封装了

不带转换回调的使用示例：
================================================
new Thread(){
            @Override
            public void run() {

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/姑娘我爱你convert.wav";//"temp/test123.wav";
                String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/姑娘我爱你out123.mp3";
                Mp3ConvertUtilHelper.convertmp3(path,pathTaret);

            }
        }.start();
        
不带回调的获取转换进度的方式： 
================================================
String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/姑娘我爱你out123.mp3";

int progress = Mp3ConvertUtilHelper.getProgress(pathTaret);

带转换回调的使用示例：
================================================
new Thread(){
            @Override
            public void run() {

                String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out测试.wav";//"temp/test123.wav";
                String pathTaret = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp/out测试789.mp3";
                Mp3ConvertUtilHelper.convertmp3(path, pathTaret, new Mp3ConvertUtil.ConvertListener() {
                    @Override
                    public void notifyConvertProgress(int progress) {
                        Log.d("ddebug","convertByHelper02 --- notifyConvertProgress = " + progress);
                    }

                    @Override
                    public void convertFinish() {
                        Log.d("ddebug","convertByHelper02 --- convertFinish");
                    }

                    @Override
                    public void convertError(String errorMsg) {
                        Log.d("ddebug","convertByHelper02 --- convertError --- " + errorMsg);
                    }
                });
                

            }

        }.start();
        
        

简单实用^_^

