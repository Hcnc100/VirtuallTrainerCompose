package com.d34th.nullpointer.virtualtrainercompose.ui.screens.camera

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidViewBinding
import androidx.lifecycle.LifecycleOwner
import com.d34th.nullpointer.virtualtrainercompose.databinding.LayoutArCoreBinding
import com.d34th.nullpointer.virtualtrainercompose.ui.share.SimpleToolbar
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode
import com.gorisse.thomas.sceneform.scene.await


@Composable
fun CameraScreen(
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
) {

    var isLoaded by remember {
        mutableStateOf(false)
    }


    var model by remember {
        mutableStateOf<ModelRenderable?>(null)
    }

    LaunchedEffect(key1 = Unit) {
        model = ModelRenderable.builder()
            .setSource(context, Uri.parse("squats.glb"))
            .setIsFilamentGltf(true)
            .await()
    }

    Scaffold(
        topBar = { SimpleToolbar("Camara") },
        floatingActionButton = {
            Button(onClick = {
                if (!isLoaded) {
                    isLoaded = true
                }
            }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->


        AndroidViewBinding(
            modifier = Modifier.padding(paddingValues),
            factory = LayoutArCoreBinding::inflate,
            update = {
                val arFragment = fragmentContainerView.getFragment<ArFragment>()
                arFragment.setOnTapArPlaneListener { hitResult, plane, motionEvent ->
                    // Create the Anchor
                    arFragment.arSceneView.scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
                        // Create the transformable model and add it to the anchor
                        addChild(TransformableNode(arFragment.transformationSystem).apply {
                            renderable = model
                            renderableInstance.animate(true).start()
                        })
                    })
                }
            }
        )
    }
}



