package com.example.art_gallery_app


import android.os.Bundle
import android.widget.Space
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
data class ArtWorkImage(
    val artWorkIndex:Int,
    val artWorkName: String,
    val painter:String,
    val year:Int
)
var artworks = mutableListOf<ArtWorkImage>(
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_1,
        artWorkName = "OakField",
        painter = "Vincent Van gough",
        year = 1999
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_2,
        artWorkName = "OakField",
        painter = "Something -1",
        year = 1999
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_3,
        artWorkName = "OakField",
        painter = "Something - 2",
        year = 1999
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_4,
        artWorkName = "OakField",
        painter = "Something -3 ",
        year = 1999
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_5,
        artWorkName = "OakField",
        painter = "Something -3 ",
        year = 1999
    )


)

private fun changeImage(isNext:Boolean=true,currentImageId:Int): ArtWorkImage{
    var index = artworks.indexOfFirst { it.artWorkIndex==currentImageId }

    if(isNext){
        index++
        return artworks[index%artworks.count()]
    }else{
        if(index==0){
            index=artworks[artworks.count()-1].artWorkIndex
        }else{
            index-=1
        }

        return artworks[index%artworks.count()]
    }
}

@Composable
fun ArtGalleryAppLayOut(modifier: Modifier= Modifier){
    var imageId by remember { mutableStateOf<Int>(artworks[0].artWorkIndex) }
    var artWorkName by remember { mutableStateOf<String> (artworks[0].artWorkName)}
    var painter by remember { mutableStateOf<String>(artworks[0].painter) }
    var year by remember { mutableStateOf<Int>(artworks[0].year) }



    Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.weight(0.5f))
            Row(Modifier.shadow(
                elevation = 3.5.dp,

            )) {
                ImageViewer(modifier.padding(32.dp),imageId)
            }
            Spacer(modifier = Modifier.weight(1.0f))
        //248, 250, 227
            Row(Modifier.padding(32.dp).background(Color(red=236, green=235, blue=244))){
                ArtWorkInfo(
                    modifier=modifier.padding(16.dp),
                    artWorkName,
                    painter,
                    year
                )
            }

            Row(Modifier.padding(bottom=10.dp, start = 32.dp,end=32.dp)){
                Button(onClick = {
                    var artWork: ArtWorkImage=changeImage(isNext = false,currentImageId = imageId)
                    imageId=artWork.artWorkIndex
                    artWorkName=artWork.artWorkName
                    painter = artWork.painter
                    year = artWork.year
                },modifier = Modifier.weight(0.5f)) {
                    Text("Previous")
                }
                Spacer(modifier = Modifier.weight(0.2f))
                Button(onClick = {
                    var artWork: ArtWorkImage=changeImage(isNext = true,currentImageId = imageId)
                    imageId=artWork.artWorkIndex
                    artWorkName=artWork.artWorkName
                    painter = artWork.painter
                    year = artWork.year
                },modifier = Modifier.weight(0.5f)) {
                    Text("Next")
                }
            }
        }


}
@Composable
fun ImageViewer(modifier: Modifier,imageId:Int){
    Image(
        modifier=modifier.wrapContentSize(Alignment.Center).fillMaxHeight(0.5f).fillMaxWidth(0.8f),
        painter = painterResource(imageId),
        contentScale = ContentScale.FillHeight,
        contentDescription = null
    )
}
@Composable
fun ArtWorkInfo(modifier: Modifier,artWorkName:String,painter:String,year:Int){
    Column(modifier){
        Text(
            text=artWorkName,
            fontSize = 25.sp,
            lineHeight = 32.sp,
            fontWeight = FontWeight.Light

        )
        ArtAuthor(year=year,painter=painter)
    }
}
@Composable
fun ArtAuthor(modifier: Modifier= Modifier,year:Int,painter:String){
    Row(){
        Text(
            text=painter
        )
        Text(
            text= "($year)"
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