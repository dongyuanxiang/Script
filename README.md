# Script
这是一个通过图片识别来实现的自动操作程序  
如果你想测试的话，请把img文件夹内的key.png替换为你的目标图片并在调用findImage时填入你需要的参数。  
目前已经支持对任意位置的前台窗口进行截图，并相对于这个窗口进行图片识别并点击。  
需要注意的是，如果您是用了相对于窗口的参数，则需要在执行操作时将操作坐标加上窗口左上角坐标相对于屏幕的值。  
如果您需要对以管理员身份打开的程序进行操作时，请将IDE/jar也以管理员身份启动，否者将出现无反应的情况。
# 鸣谢

> [IntelliJ IDEA](https://zh.wikipedia.org/zh-hans/IntelliJ_IDEA) 是一个强大的Java平台开发工具。

特别感谢 [JetBrains](https://www.jetbrains.com) 为开源项目提供免费的[IntelliJ IDEA](https://www.jetbrains.com/idea) 等 IDE 的授权  
<img src="img/JetBrains.png" width="200"/>