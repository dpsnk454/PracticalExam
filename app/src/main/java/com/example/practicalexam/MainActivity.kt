package com.example.practicalexam

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


// MainActivity
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EventDetailsScreen()
        }
    }
}


data class Event(
    val time: String,
    val title: String
)

// Define data class for Review
data class Review(
    val profileImage: Int,
    val userName: String,
    val comment: String
)

// Sample data for events and reviews
val eventList = listOf(
    Event("10:00 AM", "Opening Ceremony"),
    Event("12:00 PM", "Tech Talk 1"),
    Event("02:00 PM", "Lunch Break"),
    Event("03:00 PM", "Panel Discussion")
)

val userReviews = listOf(
    Review(R.drawable.user, "Alice", "Great event, very informative!"),
    Review(R.drawable.user, "Bob", "Loved the networking opportunities."),
    Review(R.drawable.user, "Charlie", "Well-organized and engaging.")
)

// Composable function for EventDetailsScreen
@Composable
fun EventDetailsScreen() {
    val isDarkTheme = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Scrollable Content
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 72.dp) // Add padding to avoid overlap with the buttons
            ) {
                item {
                    // Event Title and Details
                    Text(
                        "Tech Conference 2024",
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(16.dp)
                    )
                    Text(
                        "Mehsana, Gujarat | 2.5 km away",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "This is a detailed description of the event...",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )

                    // Event Image
                    Image(
                        painter = painterResource(id = R.drawable.event), // Ensure event image exists
                        contentDescription = "Event Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp),
                        contentScale = ContentScale.Crop
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // Event Schedule
                    Text(
                        "Event Schedule",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(eventList.size) { index ->
                            EventScheduleCard(eventList[index])
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // User Reviews
                    Text(
                        "User Reviews",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                items(userReviews.size) { index ->
                    UserReviewCard(userReviews[index])
                }
            }

            // Fixed Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /* Handle Buy Tickets */ }) {
                    Text("Buy Tickets")
                }
                OutlinedButton(onClick = { /* Handle Add to Calendar */ }) {
                    Text("Add to Calendar")
                }
            }
        }
    }
}




@Composable
fun EventScheduleCard(event: Event) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = event.time,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = event.title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold, // Makes the text bold
                    fontSize = 16.sp             // Increases the text size
                )
            )
        }
    }
}

@Composable
fun UserReviewCard(review: Review) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = review.profileImage), // Ensure profile image exists
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(review.userName, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(review.comment, style = MaterialTheme.typography.bodySmall)
        }
    }
}