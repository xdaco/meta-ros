# /bin/sh (deliberately no !#)
#
# Usage: cd meta-ros
#        [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/ros-generate-recipes.sh ROS_DISTRO YYYYMMDD __all__
#        (current branch is now superflore/DATETIME)
#
# where YYYYMMDD is the value for ROS_DISTRO_RELEASE_DATE, which is taken from the release announcement or the last field of the
# "release-ROS_DISTRO-YYYYMMDD" tag. Prior to the first release of ROS_DISTRO, specify "" for YYYYMMDD.
#
# XXX Once superflore is fixed to generate only recipes when given --only, have this script recognize optional PKG1 PKG2 ...
# arguments in place of "__all__" that cause "--only PKG1 PKG2 ..." to be passed to superflore.
#
# This script will abort if Git detects any uncommitted modifications, eg, from a previous run that did not complete or untracked
# files (which would otherwise appear in files/ROS_DISTRO/superflore-change-summary.txt).
#
# Copyright (c) 2019 LG Electronics, Inc.

SCRIPT_NAME="ros-generate-recipes"
SCRIPT_VERSION="1.0.0"

usage() {
    echo "Usage: cd meta-ros"
    echo "       [SUPERFLORE_GEN_OE_RECIPES=/path/to/superflore-gen-oe-recipes] sh scripts/$SCRIPT_NAME.sh ROS_DISTRO YYYYMMDD __all__"
    echo "           or"
    echo "       sh scripts/$SCRIPT_NAME.sh --version"
    exit 1
}

if [ $1 = "--version" ]; then
    echo "$SCRIPT_NAME $SCRIPT_VERSION"
    exit
fi

# XXX Eventually, this test will be changed to [ $# -ge 3 ]
[ $# -ne 3 ] && usage

[ -z "$SUPERFLORE_GEN_OE_RECIPES" ] && SUPERFLORE_GEN_OE_RECIPES=$(which superflore-gen-oe-recipes)
if [ -z "$SUPERFLORE_GEN_OE_RECIPES" ]; then
    echo "ABORT: superflore-gen-oe-recipes not found"
    exit
fi

ROS_DISTRO=$1
# ROS_VERSION and ROS_PYTHON_VERSION must be in the environment as they appear in "conditional" attributes.
case $ROS_DISTRO in
    "kinetic"|"melodic")
        export ROS_VERSION="1"
        export ROS_PYTHON_VERSION="2"
        ;;

    "crystal"|"dashing")
        export ROS_VERSION="2"
        export ROS_PYTHON_VERSION="3"
        ;;

    *)  echo "ABORT: Unrecognized ROS_DISTRO: $ROS_DISTRO"
        exit 1
        ;;
esac

if [ -n "$(git status --porcelain=v1)" ]; then
    echo "ABORT: Uncommitted modifications detected by Git -- perhaps invoke 'git reset --hard'?"
    exit 1
fi

skip_keys_option=""
ros1_lisp_packages="euslisp geneus genlisp roslisp actionlib_lisp cl_tf cl_tf2 cl_transforms cl_transforms_stamped cl_urdf cl_utils roslisp_common roslisp_utilities rosemacs ros_emacs_utils roslisp_repl slime_ros slime_wrapper"
case $ROS_DISTRO in
    "kinetic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers grpc nanomsg octovis $ros1_lisp_packages"
        ;;

    "melodic")
        skip_keys_option="--skip-keys catkin_virtualenv flatbuffers iirob_filters grpc nanomsg octovis $ros1_lisp_packages"
        ;;

    *)  : Nothing is skipped for "crystal" and "dashing".
        ;;
esac

if [ $3 = "__all__" ]; then
    only_option=""
else
    usage

    # XXX Eventually:
    shift 2
    only_option="--only $*"
fi

rosdep update

before_commit=$(git rev-list -1 HEAD)
$SUPERFLORE_GEN_OE_RECIPES --dry-run --ros-distro $ROS_DISTRO --output-repository-path . --upstream-branch HEAD \
                            $skip_keys_option $only_option

after_commit=$(git rev-list -1 HEAD)
if [ $after_commit != $before_commit ]; then
    generated="conf/ros-distro/include/$ROS_DISTRO/generated-ros-distro.inc"
    cat <<! >> $generated

# From the release announcement or the last field of the "release-ROS_DISTRO-YYYYMMDD" tag for the release in
# https://github.com/ros2/ros2/releases. Prior to the first release of a ROS_DISTRO, it is set to "".
ROS_DISTRO_RELEASE_DATE = "$2"
!

    git add $generated
    git commit --amend -q -C HEAD

    unset generated
fi
