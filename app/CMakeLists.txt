
#指定CMake构建本地库时所需的最小版本
cmake_minimum_required(VERSION 3.4.1)

#该变量为真时会创建完整版本的Makefile
set(CMAKE_VERBOSE_MAKEFILE on)
set(LAME_LIBMP3_DIR ${CMAKE_SOURCE_DIR}/src/main/jni/libmp3lame)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -DLOGTEST")
include_directories(${CMAKE_SOURCE_DIR}/src/main/jni/libmp3lame)
include_directories(${CMAKE_SOURCE_DIR}/src/main/jni)

include_directories(E/work/sdk/ndk-bundle/toolchains/llvm/prebuilt/windows-x86_64/sysroot/usr/include)
add_library( # Sets the name of the library. 将资源文件生成动态链接库（so文件）的库名称(文件名称：“lib" +设置的名称)
        hello

        # Sets the library as a shared library.
        SHARED

        # Provides a relative path to your source file(s).资源文件（C或C++）的相对位置
        src/main/jni/NativeMp3ConvertUtil.cpp
        src/main/jni/NativeMyTest.cpp)
# 这里用来添加一个库
add_library(# 这里设置so库的名称为native-lib
        libmp3lame
        # 这里设置该库为共享
        SHARED
        #源码文件
        ${LAME_LIBMP3_DIR}/bitstream.c
        ${LAME_LIBMP3_DIR}/encoder.c
        ${LAME_LIBMP3_DIR}/fft.c
        ${LAME_LIBMP3_DIR}/gain_analysis.c
        ${LAME_LIBMP3_DIR}/id3tag.c
        ${LAME_LIBMP3_DIR}/lame.c
        ${LAME_LIBMP3_DIR}/mpglib_interface.c
        ${LAME_LIBMP3_DIR}/newmdct.c
        ${LAME_LIBMP3_DIR}/presets.c
        ${LAME_LIBMP3_DIR}/psymodel.c
        ${LAME_LIBMP3_DIR}/quantize.c
        ${LAME_LIBMP3_DIR}/quantize_pvt.c
        ${LAME_LIBMP3_DIR}/reservoir.c
        ${LAME_LIBMP3_DIR}/set_get.c
        ${LAME_LIBMP3_DIR}/tables.c
        ${LAME_LIBMP3_DIR}/takehiro.c
        ${LAME_LIBMP3_DIR}/util.c
        ${LAME_LIBMP3_DIR}/vbrquantize.c
        ${LAME_LIBMP3_DIR}/VbrTag.c
        ${LAME_LIBMP3_DIR}/version.c
        )
find_library( # Sets the name of the path variable.
        log-lib

        # Specifies the name of the NDK library that
        # you want CMake to locate.
        log )

target_link_libraries( # Specifies the target library.将所有的add_library中的库链接起来，有多少个add_library成的库就将其添加到这里
        hello  #这个和add_library中的指定的so库名称一致
        libmp3lame
        # Links the target library to the log library
        # included in the NDK.
        ${log-lib} )
