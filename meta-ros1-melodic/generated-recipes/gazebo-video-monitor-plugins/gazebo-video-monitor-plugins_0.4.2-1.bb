# Generated by superflore -- DO NOT EDIT
#
# Copyright Open Source Robotics Foundation

inherit ros_distro_melodic
inherit ros_superflore_generated

DESCRIPTION = "The gazebo_video_monitor_plugins package"
AUTHOR = "Nick Lamprianidis <nlamprian@gmail.com>"
ROS_AUTHOR = "Nick Lamprianidis <nlamprian@gmail.com>"
HOMEPAGE = "https://wiki.ros.org"
SECTION = "devel"
LICENSE = "GPL-3"
LIC_FILES_CHKSUM = "file://package.xml;beginline=10;endline=10;md5=1e7b3bcc2e271699c77c769685058cbe"

ROS_CN = "gazebo_video_monitor_plugins"
ROS_BPN = "gazebo_video_monitor_plugins"

ROS_BUILD_DEPENDS = " \
    gazebo-ros \
    message-generation \
    opencv \
    roscpp \
    std-srvs \
    yaml-cpp \
"

ROS_BUILDTOOL_DEPENDS = " \
    catkin-native \
"

ROS_EXPORT_DEPENDS = " \
    gazebo-ros \
    opencv \
    roscpp \
"

ROS_BUILDTOOL_EXPORT_DEPENDS = ""

ROS_EXEC_DEPENDS = " \
    gazebo-ros \
    message-runtime \
    opencv \
    roscpp \
    std-srvs \
"

# Currently informational only -- see http://www.ros.org/reps/rep-0149.html#dependency-tags.
ROS_TEST_DEPENDS = ""

DEPENDS = "${ROS_BUILD_DEPENDS} ${ROS_BUILDTOOL_DEPENDS}"
# Bitbake doesn't support the "export" concept, so build them as if we needed them to build this package (even though we actually
# don't) so that they're guaranteed to have been staged should this package appear in another's DEPENDS.
DEPENDS += "${ROS_EXPORT_DEPENDS} ${ROS_BUILDTOOL_EXPORT_DEPENDS}"

RDEPENDS_${PN} += "${ROS_EXEC_DEPENDS}"

# matches with: https://github.com/nlamprian/gazebo_video_monitor_plugins-release/archive/release/melodic/gazebo_video_monitor_plugins/0.4.2-1.tar.gz
ROS_BRANCH ?= "branch=release/melodic/gazebo_video_monitor_plugins"
SRC_URI = "git://github.com/nlamprian/gazebo_video_monitor_plugins-release;${ROS_BRANCH};protocol=https"
SRCREV = "1dc246a9cfdacd65e5637d298b870a9116210382"
S = "${WORKDIR}/git"

ROS_BUILD_TYPE = "catkin"

inherit ros_${ROS_BUILD_TYPE}
