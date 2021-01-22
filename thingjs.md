### Thingjs

#### 1. 概念

thingjs：面向物联网可视化的开发

##### 1.1 对象

对象：thingjs提供各类创建的物体实例，不同类的对象具有不同的接口和功能

##### 1.2 模型

Thingjs平台的模型提供了可复用的3D资源包

##### 1.3 场景

启动Thingjs后，ThingJs就会创建出一个三维空间，整个空间我们称为场景

##### 1.4 园区

通过CamBuilder可编辑并输出一个园区，该园区可在Thingjs场景中加载

##### 1.5 项目

Thingjs平台的项目是指一个场景加上一个交互脚本。

##### 1.6 气泡（Marker/顶牌）

2D的一个场景

#### 2.场景

##### 2.1 创建

可以创建多个园区，但不能创建**Building/Floor**

```js
var campus = app.create({
	type:'campus',
	url:''
})
```

##### 2.2 结构

###### 2.2.1.父子树体系

访问父对象：app.root.children[1].parent

访问子对象：app.root.children

![image-20201230110312051](E:\扬州前端\学习笔记图片缓存区\image-20201230110312051.png)

###### 2.2.2 分类对象属性树体系

访问园区类对象：app.campuses

访问建筑类对象：app.buildings

访问thing类型物体对象：app.things

![image-20201230110452432](E:\扬州前端\学习笔记图片缓存区\image-20201230110452432.png)

##### 2.3 查询

###### 2.3.1 全局查询

类型搜索：.Thing  .Room .Floor .Building .Campus .Marker .Car(自定义类型)

```
//根据id查询
app.query('#2271')[0],
//根据名字
app.query('car01')[0],
//类型查询
app.query('.Building');
//根据属性
app.query('/car/').query('[userData/power>10]');
```

##### 2.4 对象类型

模型物体：**[Thing](https://www.thingjs.com/guide/cn/apidocs/THING.Thing.html)**；

基本形体：[Box](https://www.thingjs.com/guide/cn/apidocs/THING.Box.html)**；** [Sphere](https://www.thingjs.com/guide/cn/apidocs/THING.Sphere.html)**；** **[Plane](https://www.thingjs.com/guide/cn/apidocs/THING.Plane.html)**； **[Cylinder](https://www.thingjs.com/guide/cn/apidocs/THING.Cylinder.html)**； **[Tetrahedron](https://www.thingjs.com/guide/cn/apidocs/THING.Tetrahedron.html)**；

园区：**[Campus](https://www.thingjs.com/guide/cn/apidocs/THING.Campus.html)**

界面：**[UIAnchor](https://www.thingjs.com/guide/cn/apidocs/THING.UIAnchor.html)****； **[Marker](https://www.thingjs.com/guide/cn/apidocs/THING.Marker.html)***  **[WebView](https://www.thingjs.com/guide/cn/apidocs/THING.WebView.html)**

粒子：[ParticleSystem](https://www.thingjs.com/guide/cn/apidocs/THING.ParticleSystem.html)

线：[Line](https://www.thingjs.com/guide/cn/apidocs/THING.Line.html)；[RouteLine](https://www.thingjs.com/guide/cn/apidocs/THING.RouteLine.html)

热力图：**[eatmap](https://www.thingjs.com/guide/cn/apidocs/THING.Heatmap.html)**

自定义类：**Car/Deploy**

##### 2.5 控制对象

###### 2.5.1.空间坐标系

使用右手坐标系

###### 2.5.2 状态

```js
//显隐
obj.visible = true(false)
//控制对象缩放
obj.scale = [2, 2, 2]
//控制对象旋转
obj.rotate(45,[0,1,0]) //参数1：角度 参数2：轴向

//控制对象位置：
//世界坐标系下：
	position
//父物体坐标系：
	localPosition
//自身坐标系下：
	translate([x, y, z])

//控制模型动画：
//获取动画：
	animationNames
//播放动画：
	playAnimation
//停止播放：
	stopAnimation

//控制对象位移、旋转、缩放动画
//移动动画：
    moveTo()
//旋转动画：
	rotateTo()
//缩放动画：
	scaleTo()
//沿路径移动：
	movePath()
```

###### 2.5.3 物体效果

通过控制物体对象下的style属性，来实现效果

```js
//1.颜色
//设置颜色：
obj.style.color = ‘red’;
//取消颜色：
obj.style.color = null;

//2.勾边
//设置勾边：
obj.style.outlineColor = '#ff0000';
//取消勾边：
obj.style.outlineColor = null;

//3.淡入淡出
//淡入：
obj.fadeIn();
//淡出：
obj.fadeOut();

//4.发光（可以与color属性搭配）
//开启外发光：
obj.style.glow = true;
//开启内发光：
obj.style.innerGlow = true;

//5.是否受父物体影响
inheritAngles：false // 是否跟随父物体旋转
inheritPickable：false // 拾取是否受父物体影响
inheritPosition：false // 位置是否跟随父物体移动
inheritScale：false // 是否跟随父物体缩放
inheritStyle：false // 样式是否受父物体影响
inheritVisible：false // 可见性是否受父物体影响

//6.天空盒
app.skyBox = ‘Night’; //设置天空盒
app.skyBox = null; //取消天空盒
//天空盒属性值
BlueSky、MilkyWay、Night、CloudySky、White、Dark 
```

###### 2.5.4 粒子效果

```js
//粒子效果类型： 
ParticleSystem
//参数：
url : 粒子资源地址
parent: 粒子父物体
position: 粒子在世界坐标系下的位置
localPosition: 粒子在父物体坐标系下的位置
```

#### 3  中级

##### 3.1 层级切换

















