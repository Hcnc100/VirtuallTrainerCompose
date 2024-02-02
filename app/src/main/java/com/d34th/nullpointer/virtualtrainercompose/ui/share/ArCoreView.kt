package com.d34th.nullpointer.virtualtrainercompose.ui.share

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.google.ar.core.Frame
import io.github.sceneview.ar.ARScene
import io.github.sceneview.ar.arcore.createAnchorOrNull
import io.github.sceneview.ar.arcore.isValid
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader
import io.github.sceneview.rememberNodes
import io.github.sceneview.rememberOnGestureListener


@Composable
fun ArCoreView(
    model: String,
) {
    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)
    val model = modelLoader.createModel(model)
    var frame by remember { mutableStateOf<Frame?>(null) }
    val childNodes = rememberNodes()
    ARScene(
        modifier = Modifier.fillMaxSize(),
        engine = engine,
        modelLoader = modelLoader,
        onSessionUpdated = { session, updatedFrame ->
            frame = updatedFrame
        },
        onGestureListener = rememberOnGestureListener(
            onSingleTapConfirmed = { motionEvent, node ->
                val hitResults = frame?.hitTest(motionEvent.x, motionEvent.y)
                val anchor = hitResults?.firstOrNull {
                    it.isValid(depthPoint = false, point = false)
                }?.createAnchorOrNull()

                if (anchor != null) {
                    val anchorNode = io.github.sceneview.ar.node.AnchorNode(engine = engine, anchor = anchor)
                    anchorNode.addChildNode(
                        ModelNode(modelInstance = modelLoader.createInstance(model)!!)
                    )
                    childNodes += anchorNode
                }
            }
        )
    )
}