package com.d34th.nullpointer.virtualtrainercompose.ui.share

import android.animation.ObjectAnimator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidViewBinding
import com.d34th.nullpointer.virtualtrainercompose.databinding.LayoutArCoreBinding
import com.google.ar.sceneform.AnchorNode
import com.google.ar.sceneform.rendering.ModelRenderable
import com.google.ar.sceneform.ux.ArFragment
import com.google.ar.sceneform.ux.TransformableNode


@Composable
fun ArCoreView(
    isModelAdded: Boolean,
    model: ModelRenderable,
    modifier: Modifier = Modifier,
    actionTapArPlaneListener: (ObjectAnimator) -> Unit
) {

    AndroidViewBinding(
        modifier = modifier,
        factory = LayoutArCoreBinding::inflate,
        update = {
            val arFragment = fragmentContainerView.getFragment<ArFragment>()
            arFragment.setOnTapArPlaneListener { hitResult, _, _ ->
                if (!isModelAdded) {
                    arFragment.arSceneView.scene.addChild(AnchorNode(hitResult.createAnchor()).apply {
                        addChild(TransformableNode(arFragment.transformationSystem).apply {
                            renderable = model
                            actionTapArPlaneListener(renderableInstance.animate(true))
                        })
                    })
                }
            }
        }
    )
}