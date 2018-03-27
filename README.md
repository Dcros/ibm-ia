# ibm-ia
Basic IA that you can ask with voice(microphone) and IA responds with voice... Also displaying the text spoken by you and IA.

ANOTHER LIBS USED---> They are all IN LIBSY FOLDER AND ALL ARE FOR MBROLA, THE IBM WATSON SPEECH TO TEXT Are Dependencies from POM FILE.
TEXT TO SPEECH --> Im using Mrbola libs.
SPEECH TO TEXT --> Im using IBM Watson Speech to Text Api.(If you use my project be sure make an account in IBM Cloud and use your own Api Key Its free.

For now there is only one problem: When I load the libraries as dependencies of "mbrola" (which are the libraries used to pass from text to speech), This is a project "Maven Javafx" and can not load libraries just dependencies,but I have loaded the libraries as dependency and it works does not show dependencies error in the editor... so it works then the api starts well... just showing me these warnings about the libraries:

/-----------------------------------------------------------------------------------------------/

The POM for cmutimeawb:cmuutimeawb:jar:4 is missing, no dependency information available
The POM for mbrola:mbrolaia:jar:4 is missing, no dependency information available
The POM for cmukal:cmuukal:jar:4 is missing, no dependency information available
The POM for cmudict4:cmuudict4:jar:4 is missing, no dependency information available
The POM for cmulex:cmuulex:jar:4 is missing, no dependency information available
The POM for cmutimelex:cmuutimelex:jar:4 is missing, no dependency information available
The POM for enuslang:enuslang4:jar:4 is missing, no dependency information available
The POM for freetts:freeetts:jar:4 is missing, no dependency information available
The POM for jaspifreetts:jasppifreetts:jar:4 is missing, no dependency information available

/------------------------------------------------------------------------------------------------/

They appears as Warnings dont shut down the App.

But when i try to run acctualy the part where this libraries have to work they wont saying me these error messages:

/----------------------------------------------------------------------------------------------------------------/

Exception in thread "JavaFX Application Thread" java.lang.RuntimeException: java.lang.reflect.InvocationTargetException
	at javafx.fxml.FXMLLoader$MethodHandler.invoke(FXMLLoader.java:1774)
	at javafx.fxml.FXMLLoader$ControllerMethodEventHandler.handle(FXMLLoader.java:1657)
	at com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:86)
	at com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:238)
	at com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
	at com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
	at com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:49)
	at javafx.event.Event.fireEvent(Event.java:198)
	at javafx.scene.Node.fireEvent(Node.java:8413)
	at javafx.scene.control.Button.fire(Button.java:185)
	at com.sun.javafx.scene.control.behavior.ButtonBehavior.mouseReleased(ButtonBehavior.java:182)
	at com.sun.javafx.scene.control.skin.BehaviorSkinBase$1.handle(BehaviorSkinBase.java:96)
	at com.sun.javafx.scene.control.skin.BehaviorSkinBase$1.handle(BehaviorSkinBase.java:89)
	at com.sun.javafx.event.CompositeEventHandler$NormalEventHandlerRecord.handleBubblingEvent(CompositeEventHandler.java:218)
	at com.sun.javafx.event.CompositeEventHandler.dispatchBubblingEvent(CompositeEventHandler.java:80)
	at com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:238)
	at com.sun.javafx.event.EventHandlerManager.dispatchBubblingEvent(EventHandlerManager.java:191)
	at com.sun.javafx.event.CompositeEventDispatcher.dispatchBubblingEvent(CompositeEventDispatcher.java:59)
	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:58)
	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at com.sun.javafx.event.BasicEventDispatcher.dispatchEvent(BasicEventDispatcher.java:56)
	at com.sun.javafx.event.EventDispatchChainImpl.dispatchEvent(EventDispatchChainImpl.java:114)
	at com.sun.javafx.event.EventUtil.fireEventImpl(EventUtil.java:74)
	at com.sun.javafx.event.EventUtil.fireEvent(EventUtil.java:54)
	at javafx.event.Event.fireEvent(Event.java:198)
	at javafx.scene.Scene$MouseHandler.process(Scene.java:3757)
	at javafx.scene.Scene$MouseHandler.access$1500(Scene.java:3485)
	at javafx.scene.Scene.impl_processMouseEvent(Scene.java:1762)
	at javafx.scene.Scene$ScenePeerListener.mouseEvent(Scene.java:2494)
	at com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:394)
	at com.sun.javafx.tk.quantum.GlassViewEventHandler$MouseEventNotification.run(GlassViewEventHandler.java:295)
	at java.security.AccessController.doPrivileged(Native Method)
	at com.sun.javafx.tk.quantum.GlassViewEventHandler.lambda$handleMouseEvent$353(GlassViewEventHandler.java:432)
	at com.sun.javafx.tk.quantum.QuantumToolkit.runWithoutRenderLock(QuantumToolkit.java:389)
	at com.sun.javafx.tk.quantum.GlassViewEventHandler.handleMouseEvent(GlassViewEventHandler.java:431)
	at com.sun.glass.ui.View.handleMouseEvent(View.java:555)
	at com.sun.glass.ui.View.notifyMouse(View.java:937)
	at com.sun.glass.ui.win.WinApplication._runLoop(Native Method)
	at com.sun.glass.ui.win.WinApplication.lambda$null$147(WinApplication.java:177)
	at java.lang.Thread.run(Thread.java:748)
Caused by: java.lang.reflect.InvocationTargetException
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.reflect.misc.Trampoline.invoke(MethodUtil.java:71)
	at sun.reflect.GeneratedMethodAccessor1.invoke(Unknown Source)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at sun.reflect.misc.MethodUtil.invoke(MethodUtil.java:275)
	at javafx.fxml.FXMLLoader$MethodHandler.invoke(FXMLLoader.java:1769)
	... 48 more
Caused by: java.lang.NullPointerException
	at com.jarvia.jarvia.FXMLController.handleButtonAction(FXMLController.java:103)
	... 58 more
	
  /----------------------------------------------------------------------------------------------------------------/
  
If someone know how i can charge better the libraries in a maven javafx project please just let a coment or something my email its : ftw.dcross@gmail.com
