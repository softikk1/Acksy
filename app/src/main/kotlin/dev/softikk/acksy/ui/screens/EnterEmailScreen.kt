package dev.softikk.acksy.ui.screens

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import dev.softikk.acksy.ui.navigation.Routes
import dev.softikk.acksy.ui.theme.AcksyTheme
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.softikk.acksy.R

@Composable
fun EnterEmailScreen(modifier: Modifier = Modifier, backStack: NavBackStack<NavKey>){
    var emailText by remember { mutableStateOf("")}
    AcksyTheme {
        Box(
            modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier
                    .fillMaxSize()
                    .padding(
                        top = 16.dp,
                        bottom = 36.dp,
                        start = 18.dp,
                        end = 18.dp
                    )
            ) {
                Box(
                    modifier
                        .size(48.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            backStack.add(Routes.Welcome)
                        }
                    ) {
                    Image(
                        imageVector = ImageVector
                            .vectorResource(R.drawable.arrow_back),
                        contentDescription = "Back",
                        colorFilter = ColorFilter
                            .tint(MaterialTheme.colorScheme.onSurfaceVariant)
                    )
                }
                Box(
                    modifier.
                    fillMaxWidth()
                        .padding(top = 76.5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Choose a login email",
                        color = MaterialTheme.colorScheme.onSurface,
                        fontStyle = FontStyle.Normal,
                        fontSize = 20.sp
                    )
                }
                Box(modifier
                    .fillMaxWidth()
                    .padding(
                        top = 16.dp,
                        start = 56.dp,
                        end = 56.dp,
                        bottom = 13.5.dp
                    ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = "Please enter an email address to which you have access.",
                        fontStyle = FontStyle.Normal,
                        textAlign = TextAlign.Center,
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                Box(modifier
                    .fillMaxWidth()
                    .padding(top = 36.dp,
                        start = 18.dp,
                        end = 18.dp),
                    contentAlignment = Alignment.Center){
                    TextField(
                        value = emailText,
                        onValueChange = {emailText = it},
                        modifier = modifier.width(376.dp).height(56.dp),
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.Transparent,
                            focusedContainerColor = MaterialTheme.colorScheme.secondary,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedPlaceholderColor = MaterialTheme.colorScheme.onSecondary
                        ),
                        shape = RoundedCornerShape(8.dp),
                        placeholder = {Text("Email")},
                        singleLine = true,
                        trailingIcon = {
                            Box(modifier
                                .size(48.dp)
                                .clickable(
                                    indication = null,
                                    interactionSource = remember { MutableInteractionSource() }
                                ) {
                                    emailText = ""
                                  },
                                contentAlignment = Alignment.Center) {
                                Image(
                                    imageVector = ImageVector.vectorResource(R.drawable.x),
                                    contentDescription = "Clear",
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary)
                                )
                            }
                        }
                    )
                }
                Box(Modifier
                    .padding(
                        top = 36.dp,
                        start = 18.dp,
                        end = 18.dp,

                    )
                    .width(376.dp)
                    .height(56.dp)
                ){
                    Button(onClick = {
                        TODO()
                    }, modifier.width(376.dp).height(56.dp), colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.surface
                    ), shape = RoundedCornerShape(5.dp) ) {
                        Text(text = "Continue", fontStyle = FontStyle.Normal)
                    }
                }
                    }
                }
            }
        }


@Preview
@Composable
fun showScreen(){
    val mockBackStack = NavBackStack<NavKey>()

    EnterEmailScreen(Modifier, backStack = mockBackStack)
}