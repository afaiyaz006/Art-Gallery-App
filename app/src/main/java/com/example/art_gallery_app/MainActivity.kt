package com.example.art_gallery_app


import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.art_gallery_app.ui.theme.ArtGalleryAppTheme
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        enableEdgeToEdge()
        setContent {

            ArtGalleryAppTheme {
                Surface {
                    val windowSizeClass = calculateWindowSizeClass(this)

                    when (windowSizeClass.widthSizeClass) {
                        WindowWidthSizeClass.Compact -> ArtGalleryAppLayOut(modifier = Modifier.systemBarsPadding())
                        WindowWidthSizeClass.Medium -> ArtGalleryAppLayOutMedium(modifier = Modifier.systemBarsPadding())
                        WindowWidthSizeClass.Expanded -> ArtGalleryAppLayOutEpanded(modifier = Modifier.systemBarsPadding())


                    }
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
        artWorkIndex = R.drawable.caspar_david_friedrich___wanderer_above_the_sea_of_fog,
        artWorkName = "Wonderer above the sea fog",
        painter = " Caspar David Friedrich",
        year = 1817
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_2,
        artWorkName = "Bay of Naples",
        painter = "Jakob Philipp Hackert",
        year = 1899
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_3,
        artWorkName = "Landscape in the Riesengebirge",
        painter = "Johann Wilhelm Schirmer",
        year = 1913
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_4,
        artWorkName = "The Artist's Garden in Argenteuil",
        painter = "Claude Monet",
        year = 1999
    ),
    ArtWorkImage(
        artWorkIndex = R.drawable.nature_5,
        artWorkName = "Extensive landscape prospect with a fortified building on hill in the background",
        painter = "Antoine Chintreuil",
        year = 1950
    )


)

private fun changeImage(isNext:Boolean=true,currentImageId:Int): ArtWorkImage{
    var index = artworks.indexOfFirst { it.artWorkIndex==currentImageId }
    if(isNext){
        index++
        return artworks[index%artworks.count()]
    }else{
        index-=1
        index=(index+artworks.count())%artworks.count()
        return artworks[index]
    }
}

@Composable
fun ArtGalleryAppLayOut(modifier: Modifier= Modifier){
    var imageId by remember { mutableStateOf<Int>(artworks[0].artWorkIndex) }
    var artWorkName by remember { mutableStateOf<String> (artworks[0].artWorkName)}
    var painter by remember { mutableStateOf<String>(artworks[0].painter) }
    var year by remember { mutableStateOf<Int>(artworks[0].year) }



    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.weight(0.5f))
            Row(Modifier.shadow(
                elevation = 3.5.dp,

            )) {
                ImageViewer(modifier.padding(32.dp).wrapContentSize(Alignment.Center).fillMaxHeight(0.5f).fillMaxWidth(0.8f),imageId)
            }
            Spacer(modifier = Modifier.weight(0.6f))
        //248, 250, 227
            Row(Modifier.padding(32.dp).background(Color(red=236, green=235, blue=244))){
                ArtWorkInfo(
                    modifier=modifier.padding(16.dp),
                    artWorkName,
                    painter,
                    year
                )
            }

            Row(Modifier.padding(bottom=10.dp, start = 32.dp,end=32.dp).fillMaxWidth()){
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
fun ArtGalleryAppLayOutMedium(modifier: Modifier= Modifier){
    var imageId by remember { mutableStateOf<Int>(artworks[0].artWorkIndex) }
    var artWorkName by remember { mutableStateOf<String> (artworks[0].artWorkName)}
    var painter by remember { mutableStateOf<String>(artworks[0].painter) }
    var year by remember { mutableStateOf<Int>(artworks[0].year) }



    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(0.5f))
        Row(Modifier.shadow(
            elevation = 3.5.dp,

            )) {
            ImageViewer(modifier.padding(32.dp).wrapContentSize(Alignment.Center).fillMaxHeight(0.5f).width(300.dp),imageId)
        }

        //248, 250, 227
        Row(Modifier.padding(32.dp).background(Color(red=236, green=235, blue=244))){
            ArtWorkInfo(
                modifier=modifier.padding(16.dp),
                artWorkName,
                painter,
                year
            )
        }

        Row(Modifier.fillMaxWidth().padding(bottom=10.dp, start = 32.dp,end=32.dp)){
            Button(onClick = {
                var artWork: ArtWorkImage=changeImage(isNext = false,currentImageId = imageId)
                imageId=artWork.artWorkIndex
                artWorkName=artWork.artWorkName
                painter = artWork.painter
                year = artWork.year
            },modifier = Modifier.weight(0.2f)) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.weight(0.8f))
            Button(onClick = {
                var artWork: ArtWorkImage=changeImage(isNext = true,currentImageId = imageId)
                imageId=artWork.artWorkIndex
                artWorkName=artWork.artWorkName
                painter = artWork.painter
                year = artWork.year
            },modifier = Modifier.weight(0.2f)) {
                Text("Next")
            }
        }
    }


}

@Composable
fun ArtGalleryAppLayOutEpanded(modifier: Modifier= Modifier){
    var imageId by remember { mutableStateOf<Int>(artworks[0].artWorkIndex) }
    var artWorkName by remember { mutableStateOf<String> (artworks[0].artWorkName)}
    var painter by remember { mutableStateOf<String>(artworks[0].painter) }
    var year by remember { mutableStateOf<Int>(artworks[0].year) }



    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(0.5f))
        Row(Modifier.shadow(
            elevation = 3.5.dp,

            )) {
            ImageViewer(modifier.padding(32.dp).wrapContentSize(Alignment.Center).fillMaxHeight(0.5f).fillMaxWidth(0.2f),imageId)
        }
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
            },modifier = Modifier.fillMaxWidth(0.1f)) {
                Text("Previous")
            }
            Spacer(modifier = Modifier.weight(0.9f))
            Button(onClick = {
                var artWork: ArtWorkImage=changeImage(isNext = true,currentImageId = imageId)
                imageId=artWork.artWorkIndex
                artWorkName=artWork.artWorkName
                painter = artWork.painter
                year = artWork.year
            },modifier = Modifier.weight(0.1f)) {
                Text("Next")
            }
        }
    }


}

@Composable
fun ImageViewer(modifier: Modifier,imageId:Int){
    Image(
        modifier=modifier,
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
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start


        )
        ArtAuthor(year=year,painter=painter)
    }
}
@Composable
fun ArtAuthor(modifier: Modifier= Modifier,year:Int,painter:String){
    Row(){
        Text(
            text=painter,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Start

        )
        Text(
            text= "($year)",
            modifier= Modifier.padding(start = 5.dp)
        )
    }
}
/*Mobile view*/
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    ArtGalleryAppTheme {
        Surface(modifier = Modifier.fillMaxSize()){
            ArtGalleryAppLayOut()
        }
    }
}
/*Tablet view*/
@Preview(
    name = "Tablet Preview",
    showBackground = true,
    device = "spec:width=1280dp,height=800dp,dpi=320"
)
@Composable
fun GreetingTabletPreview() {
    ArtGalleryAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            ArtGalleryAppLayOutMedium()
        }
    }
}
