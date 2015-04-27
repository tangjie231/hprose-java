/**********************************************************\
|                                                          |
|                          hprose                          |
|                                                          |
| Official WebSite: http://www.hprose.com/                 |
|                   http://www.hprose.org/                 |
|                                                          |
\**********************************************************/
/**********************************************************\
 *                                                        *
 * DateTimeSerializer.java                                *
 *                                                        *
 * DateTime serializer class for Java.                    *
 *                                                        *
 * LastModified: Apr 26, 2015                             *
 * Author: Ma Bingyao <andot@hprose.com>                  *
 *                                                        *
\**********************************************************/

package hprose.io.serialize;

import hprose.io.HproseHelper;
import static hprose.io.HproseTags.TagSemicolon;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;

final class DateTimeSerializer implements HproseSerializer<Date> {

    public final static DateTimeSerializer instance = new DateTimeSerializer();

    public final static void write(OutputStream stream, WriterRefer refer, Date date) throws IOException {
        if (refer != null) refer.set(date);
        Calendar calendar = Calendar.getInstance(HproseHelper.DefaultTZ);
        calendar.setTime(date);
        ValueWriter.writeDateOfCalendar(stream, calendar);
        ValueWriter.writeTimeOfCalendar(stream, calendar, true, false);
        stream.write(TagSemicolon);
    }

    public final void write(HproseWriterImpl writer, Date obj) throws IOException {
        OutputStream stream = writer.stream;
        WriterRefer refer = writer.refer;
        if (refer == null || !refer.write(stream, obj)) {
            write(stream, refer, obj);
        }
    }
}