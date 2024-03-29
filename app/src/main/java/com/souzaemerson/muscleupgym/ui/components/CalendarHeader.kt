package com.souzaemerson.muscleupgym.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.souzaemerson.muscleupgym.data.source.local.calendar.CalendarDataSource
import com.souzaemerson.muscleupgym.ui.components.item.CalendarItem
import com.souzaemerson.muscleupgym.ui.extensions.getMonthInPortuguese
import java.time.LocalDate

@Composable
fun CalendarHeader(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(12.dp)
    ) {
        DateHeader()
        DateContent()
    }
}

@Composable
fun DateHeader() {
    Text(
        modifier = Modifier,
        text = CalendarDataSource.getHeaderPhrase(),
        fontSize = 22.sp,
        fontWeight = FontWeight.Light,
        color = Color.White
    )
}

@Composable
fun DateContent() {
    val dates = remember { mutableListOf<LocalDate>() }

    LazyRow(
        modifier = Modifier.padding(top = 4.dp),
        horizontalArrangement = Arrangement.Center,
        state = rememberLazyListState(initialFirstVisibleItemIndex = 7)
    ) {

        items(items = CalendarDataSource.setRangeOfDates(dates)) { date ->
            CalendarItem(
                month = date.getMonthInPortuguese()
                    .removeRange(3, date.getMonthInPortuguese().length),
                day = date.dayOfMonth.toString(),
                nameOfDay = date.dayOfWeek.name.removeRange(3, date.dayOfWeek.name.length),
                color = if (date.isBefore(LocalDate.now())) Color.LightGray else Color.White
            )
        }
    }
}

@Composable
@Preview(showSystemUi = true, showBackground = true)
fun CalendarHeaderPreview(modifier: Modifier = Modifier) {
    CalendarHeader()
}