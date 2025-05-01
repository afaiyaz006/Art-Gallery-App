package com.example.art_gallery_app


import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_gallery_app.ui.theme.ArtGalleryAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtGalleryAppTheme {
                Surface(){
                    ArtGalleryAppLayOut(

                    )
                }
            }
        }
    }
}
@Composable
fun ArtGalleryAppLayOut(modifier: Modifier= Modifier){

        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.weight(0.5f))
            Row() {
                ImageViewer(modifier.padding(32.dp))
            }
            Spacer(modifier = Modifier.weight(1.0f))

            Row(Modifier.padding(32.dp)){
                ArtWorkInfo(modifier.padding(16.dp))
            }

            Row(Modifier.padding(bottom=2.dp, start = 32.dp,end=32.dp)){
                Button(onClick = {  /*nothing*/},modifier = Modifier.weight(0.5f)) {
                    Text("Previous")
                }
                Spacer(modifier = Modifier.weight(0.2f))
                Button(onClick = {  /*nothing*/},modifier = Modifier.weight(0.5f)) {
                    Text("Next")
                }
            }
        }


}
@Composable
fun ImageViewer(modifier: Modifier){
    var imageValue = painterResource(R.drawable.ic_launcher_background)
    Image(
        modifier=modifier.wrapContentSize(Alignment.Center).fillMaxHeight(0.6f).fillMaxWidth(0.9f),
        painter = imageValue,
        contentScale = ContentScale.FillHeight,
        contentDescription = null
    )
}
@Composable
fun ArtWorkInfo(modifier: Modifier){
    Column(modifier){
        Text(
            text="Still life of blue rose and other flowers",
            fontSize = 25.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Light

        )
        ArtAuthor()
    }
}
@Composable
fun ArtAuthor(modifier: Modifier= Modifier){
    Row(){
        Text(
            text="Franz Kafka"
        )
        Text(
            text="(2017)"
        )
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtGalleryAppTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            ArtGalleryAppLayOut()
        }
    }
}