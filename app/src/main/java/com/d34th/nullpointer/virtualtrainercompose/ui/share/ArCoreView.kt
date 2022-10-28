package com.d34th.nullpointer.virtualtrainercompose.ui.share
//
//import android.content.Context
//import android.net.Uri
//import androidx.appcompat.app.AppCompatActivity
//import androidx.compose.runtime.*
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.platform.LocalLifecycleOwner
//import androidx.compose.ui.viewinterop.AndroidView
//import androidx.lifecycle.LifecycleOwner
//import com.google.ar.sceneform.rendering.ModelRenderable
//import io.github.sceneview.ar.ArSceneView
//import io.github.sceneview.math.Position
//import io.github.sceneview.math.Rotation
//import io.github.sceneview.math.Scale
//import io.github.sceneview.model.await
//import io.github.sceneview.node.ModelNode
//
//@Composable
//fun ArCoreViewCompose(
////    exercise: Exercise,
//    modifier: Modifier = Modifier,
//    context: Context = LocalContext.current,
//    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
//) {
//
//    val nodeModel = remember {
//        ModelNode(
//            position = Position(x = 0.0f, y = 0.0f, z = -4.0f),
//            rotation = Rotation(y = 90.0f),
//            scale = Scale(0.5f)
//        )
//    }
//    AndroidView(factory = { ArSceneView(context = it).apply {
//        addChild(nodeModel)
//    }},
//        update = {
//            it.addChild(
//                ModelNode(
//                    position = Position(x = 0.0f, y = 0.0f, z = -4.0f),
//                    rotation = Rotation(y = 90.0f),
//                    scale = Scale(0.5f)
//                ).loadModelAsync(
//                    context = context,
//                    lifecycle = lifecycleOwner.lifecycle,
//                    glbFileLocation = "squat.glb",
//                    autoAnimate = true,
//                    centerOrigin = null,
//                    onError = { exception ->
//                    },
//                    onLoaded = { modelInstance -> }
//                )
//            )
//        }
//    )
//}