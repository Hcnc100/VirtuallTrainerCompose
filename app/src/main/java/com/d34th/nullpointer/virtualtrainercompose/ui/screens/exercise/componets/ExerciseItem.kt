package com.d34th.nullpointer.virtualtrainercompose.ui.screens.exercise.componets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.d34th.nullpointer.virtualtrainercompose.R
import com.d34th.nullpointer.virtualtrainercompose.models.Exercise

@Composable
fun ExerciseItem(
    exercise: Exercise,
    onClickExercise: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .padding(4.dp)
            .clickable { onClickExercise() }
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = exercise.image,
                modifier = Modifier.size(100.dp),
                contentDescription = stringResource(
                    R.string.description_exercise_item,
                    exercise.description
                )
            )
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.h5,
                    text = stringResource(id = exercise.title)
                )
                Text(
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.caption,
                    text = stringResource(id = exercise.description)
                )
            }
        }
    }
}

@Preview
@Composable
private fun ExerciseItemPreview() {
    ExerciseItem(
        exercise = Exercise.example,
        onClickExercise = { /*TODO*/ }
    )
}