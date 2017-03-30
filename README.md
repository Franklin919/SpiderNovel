上周，老美有个小伙，看小说治好了毒瘾，真的是23333。国产小说犹如辣条一般，风靡一股世界风！作为一名大学生，看付费小说真的有点伤不起！随手百度一下子自己想看的小说，发现好多小说网站，而且他们这些网站是没经过作者的同意就把小说放上去，这种行为太无耻了！所以我觉得给他们的服务器增加点压力，写个爬虫爬下小说下来，<strong>一</strong>来惩罚了这些无良小说网站，<strong>二</strong>来我也可以看看小说。
***
<strong>步骤一</strong>：
随便找到一个小说网站

![小说网站.png](http://upload-images.jianshu.io/upload_images/3435345-6be4b61a2408cc5a.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
***
<strong>步骤二</strong>：
分析域名

![分析域名.png](http://upload-images.jianshu.io/upload_images/3435345-bf9f720f60f17112.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)
我把域名后面的619223改为619224就变成第二章了，所以后面数字+1，章数就会+1啦。
***
<strong>步骤三</strong>：
撸码
```javascript

        //利用Jsoup获取这个网页的Html
		Document doc = Jsoup.connect(url).get();
		//通过浏览器F12，知道小说内容在Content里面
		Element contentOrigin = doc.getElementById("content");
		//拿出Content的内容
		String contentText = contentOrigin.html();
		//把<br>,&nbsp;无用的字符换成空
		String  contentFinally= contentText.replaceAll("<br>|&nbsp;","");
		return contentFinally;

```
这时候我们其实就可以拿到一章小说，可是我们要很多章的，这时候就利用步骤二我们分析的url，用个循环自己获取下一章小说。
然后将每次获取到的小说加到我们的本地盘上。
```javascript
PrintStream ps = new PrintStream(new FileOutputStream(new File("本地路径", "小说名.txt")));
ps.println("");//一开始用个空字符到小说里面，后面才追加新的小说内容
ps.append("下一章内容")//追加下一章内容
```
***
运行结果：

![运行结果.gif](http://upload-images.jianshu.io/upload_images/3435345-ce16cd882c0b00ec.gif?imageMogr2/auto-orient/strip)
***
最后结果：

![最后结果.gif](http://upload-images.jianshu.io/upload_images/3435345-cb2b9ef8e81eeb62.gif?imageMogr2/auto-orient/strip)
