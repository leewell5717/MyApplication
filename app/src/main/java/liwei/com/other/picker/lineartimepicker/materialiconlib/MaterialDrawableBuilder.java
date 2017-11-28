package liwei.com.other.picker.lineartimepicker.materialiconlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;

/**
 * Builder used to create a MaterialDrawable. Provide a context and at least an icon to build.
 *
 * Example usage:
 * <p></p>
 * <pre>
 *     Drawable myDrawable = MaterialDrawableBuilder.with(context)
 *              .setIcon(IconValue.ACCESS_POINT)
 *              .setColor(Color.RED)
 *              .setSizeDp(48)
 *     .build();
 * </pre>
 */
public class MaterialDrawableBuilder {

    public static enum IconValue {VECTOR_SQUARE,ACCESS_POINT,ACCESS_POINT_NETWORK,ACCOUNT,ACCOUNT_ALERT,ACCOUNT_BOX,ACCOUNT_BOX_OUTLINE,ACCOUNT_CHECK,ACCOUNT_CIRCLE,ACCOUNT_CONVERT,ACCOUNT_KEY,ACCOUNT_LOCATION,ACCOUNT_MINUS,ACCOUNT_MULTIPLE,ACCOUNT_MULTIPLE_OUTLINE,ACCOUNT_MULTIPLE_PLUS,ACCOUNT_NETWORK,ACCOUNT_OFF,ACCOUNT_OUTLINE,ACCOUNT_PLUS,ACCOUNT_REMOVE,ACCOUNT_SEARCH,ACCOUNT_STAR,ORBIT,ACCOUNT_SWITCH,ADJUST,AIR_CONDITIONER,AIRBALLOON,AIRPLANE,AIRPLANE_OFF,AIRPLAY,ALARM,ALARM_CHECK,ALARM_MULTIPLE,ALARM_OFF,ALARM_PLUS,ALBUM,ALERT,ALERT_BOX,ALERT_CIRCLE,ALERT_OCTAGON,ALERT_OUTLINE,ALPHA,ALPHABETICAL,AMAZON,AMAZON_CLOUDDRIVE,AMBULANCE,AMPLIFIER,ANCHOR,ANDROID,ANDROID_DEBUG_BRIDGE,ANDROID_STUDIO,APPLE,APPLE_FINDER,APPLE_IOS,APPLE_MOBILEME,APPLE_SAFARI,FONT_AWESOME,APPS,ARCHIVE,ARRANGE_BRING_FORWARD,ARRANGE_BRING_TO_FRONT,ARRANGE_SEND_BACKWARD,ARRANGE_SEND_TO_BACK,ARROW_ALL,ARROW_BOTTOM_LEFT,ARROW_BOTTOM_RIGHT,ARROW_COLLAPSE_ALL,ARROW_DOWN,ARROW_DOWN_THICK,ARROW_DOWN_BOLD_CIRCLE,ARROW_DOWN_BOLD_CIRCLE_OUTLINE,ARROW_DOWN_BOLD_HEXAGON_OUTLINE,ARROW_DOWN_DROP_CIRCLE,ARROW_DOWN_DROP_CIRCLE_OUTLINE,ARROW_EXPAND_ALL,ARROW_LEFT,ARROW_LEFT_THICK,ARROW_LEFT_BOLD_CIRCLE,ARROW_LEFT_BOLD_CIRCLE_OUTLINE,ARROW_LEFT_BOLD_HEXAGON_OUTLINE,ARROW_LEFT_DROP_CIRCLE,ARROW_LEFT_DROP_CIRCLE_OUTLINE,ARROW_RIGHT,ARROW_RIGHT_THICK,ARROW_RIGHT_BOLD_CIRCLE,ARROW_RIGHT_BOLD_CIRCLE_OUTLINE,ARROW_RIGHT_BOLD_HEXAGON_OUTLINE,ARROW_RIGHT_DROP_CIRCLE,ARROW_RIGHT_DROP_CIRCLE_OUTLINE,ARROW_TOP_LEFT,ARROW_TOP_RIGHT,ARROW_UP,ARROW_UP_THICK,ARROW_UP_BOLD_CIRCLE,ARROW_UP_BOLD_CIRCLE_OUTLINE,ARROW_UP_BOLD_HEXAGON_OUTLINE,ARROW_UP_DROP_CIRCLE,ARROW_UP_DROP_CIRCLE_OUTLINE,ASSISTANT,AT,ATTACHMENT,AUDIOBOOK,AUTO_FIX,AUTO_UPLOAD,AUTORENEW,AV_TIMER,BABY,BACKBURGER,BACKSPACE,BACKUP_RESTORE,BANK,BARCODE,BARCODE_SCAN,BARLEY,BARREL,BASECAMP,BASKET,BASKET_FILL,BASKET_UNFILL,BATTERY,BATTERY_10,BATTERY_20,BATTERY_30,BATTERY_40,BATTERY_50,BATTERY_60,BATTERY_70,BATTERY_80,BATTERY_90,BATTERY_ALERT,BATTERY_CHARGING,BATTERY_CHARGING_100,BATTERY_CHARGING_20,BATTERY_CHARGING_30,BATTERY_CHARGING_40,BATTERY_CHARGING_60,BATTERY_CHARGING_80,BATTERY_CHARGING_90,BATTERY_MINUS,BATTERY_NEGATIVE,BATTERY_OUTLINE,BATTERY_PLUS,BATTERY_POSITIVE,BATTERY_UNKNOWN,BEACH,FLASK,FLASK_EMPTY,FLASK_EMPTY_OUTLINE,FLASK_OUTLINE,BEATS,BEER,BEHANCE,BELL,BELL_OFF,BELL_OUTLINE,BELL_PLUS,BELL_RING,BELL_RING_OUTLINE,BELL_SLEEP,BETA,BIBLE,BIKE,BING,BINOCULARS,BIO,BIOHAZARD,BITBUCKET,BLACK_MESA,BLACKBERRY,BLENDER,BLINDS,BLOCK_HELPER,BLOGGER,BLUETOOTH,BLUETOOTH_AUDIO,BLUETOOTH_CONNECT,BLUETOOTH_OFF,BLUETOOTH_SETTINGS,BLUETOOTH_TRANSFER,BLUR,BLUR_LINEAR,BLUR_OFF,BLUR_RADIAL,BONE,BOOK,BOOK_MULTIPLE,BOOK_MULTIPLE_VARIANT,BOOK_OPEN,BOOK_OPEN_VARIANT,BOOK_VARIANT,BOOKMARK,BOOKMARK_CHECK,BOOKMARK_MUSIC,BOOKMARK_OUTLINE,BOOKMARK_PLUS_OUTLINE,BOOKMARK_PLUS,BOOKMARK_REMOVE,BORDER_ALL,BORDER_BOTTOM,BORDER_COLOR,BORDER_HORIZONTAL,BORDER_INSIDE,BORDER_LEFT,BORDER_NONE,BORDER_OUTSIDE,BORDER_RIGHT,BORDER_STYLE,BORDER_TOP,BORDER_VERTICAL,BOWLING,BOX,BOX_CUTTER,BRIEFCASE,BRIEFCASE_CHECK,BRIEFCASE_DOWNLOAD,BRIEFCASE_UPLOAD,BRIGHTNESS_1,BRIGHTNESS_2,BRIGHTNESS_3,BRIGHTNESS_4,BRIGHTNESS_5,BRIGHTNESS_6,BRIGHTNESS_7,BRIGHTNESS_AUTO,BROOM,BRUSH,BUG,BULLETIN_BOARD,BULLHORN,BUS,CACHED,CAKE,CAKE_LAYERED,CAKE_VARIANT,CALCULATOR,CALENDAR,CALENDAR_BLANK,CALENDAR_CHECK,CALENDAR_CLOCK,CALENDAR_MULTIPLE,CALENDAR_MULTIPLE_CHECK,CALENDAR_PLUS,CALENDAR_REMOVE,CALENDAR_TEXT,CALENDAR_TODAY,CALL_MADE,CALL_MERGE,CALL_MISSED,CALL_RECEIVED,CALL_SPLIT,CAMCORDER,CAMCORDER_BOX,CAMCORDER_BOX_OFF,CAMCORDER_OFF,CAMERA,CAMERA_ENHANCE,CAMERA_FRONT,CAMERA_FRONT_VARIANT,CAMERA_IRIS,CAMERA_PARTY_MODE,CAMERA_REAR,CAMERA_REAR_VARIANT,CAMERA_SWITCH,CAMERA_TIMER,CANDYCANE,CAR,CAR_BATTERY,CAR_CONNECTED,CAR_WASH,CARROT,CART,CART_OUTLINE,CART_PLUS,CASE_SENSITIVE_ALT,CASH,CASH_100,CASH_MULTIPLE,CASH_USD,CAST,CAST_CONNECTED,CASTLE,CAT,CELLPHONE,CELLPHONE_ANDROID,CELLPHONE_BASIC,CELLPHONE_DOCK,CELLPHONE_IPHONE,CELLPHONE_LINK,CELLPHONE_LINK_OFF,CELLPHONE_SETTINGS,CERTIFICATE,CHAIR_SCHOOL,CHART_ARC,CHART_AREASPLINE,CHART_BAR,CHART_HISTOGRAM,CHART_LINE,CHART_PIE,CHECK,CHECK_ALL,CHECKBOX_BLANK,CHECKBOX_BLANK_CIRCLE,CHECKBOX_BLANK_CIRCLE_OUTLINE,CHECKBOX_BLANK_OUTLINE,CHECKBOX_MARKED,CHECKBOX_MARKED_CIRCLE,CHECKBOX_MARKED_CIRCLE_OUTLINE,CHECKBOX_MARKED_OUTLINE,CHECKBOX_MULTIPLE_BLANK,CHECKBOX_MULTIPLE_BLANK_OUTLINE,CHECKBOX_MULTIPLE_MARKED,CHECKBOX_MULTIPLE_MARKED_OUTLINE,CHECKERBOARD,CHEMICAL_WEAPON,CHEVRON_DOUBLE_DOWN,CHEVRON_DOUBLE_LEFT,CHEVRON_DOUBLE_RIGHT,CHEVRON_DOUBLE_UP,CHEVRON_DOWN,CHEVRON_LEFT,CHEVRON_RIGHT,CHEVRON_UP,CHURCH,CISCO_WEBEX,CITY,CLIPBOARD,CLIPBOARD_ACCOUNT,CLIPBOARD_ALERT,CLIPBOARD_ARROW_DOWN,CLIPBOARD_ARROW_LEFT,CLIPBOARD_CHECK,CLIPBOARD_OUTLINE,CLIPBOARD_TEXT,CLIPPY,CLOCK,CLOCK_END,CLOCK_FAST,CLOCK_IN,CLOCK_OUT,CLOCK_START,CLOSE,CLOSE_BOX,CLOSE_BOX_OUTLINE,CLOSE_CIRCLE,CLOSE_CIRCLE_OUTLINE,CLOSE_NETWORK,CLOSE_OCTAGON,CLOSE_OCTAGON_OUTLINE,CLOSED_CAPTION,CLOUD,CLOUD_CHECK,CLOUD_CIRCLE,CLOUD_DOWNLOAD,CLOUD_OUTLINE,CLOUD_OFF_OUTLINE,CLOUD_PRINT,CLOUD_PRINT_OUTLINE,CLOUD_UPLOAD,CODE_ARRAY,CODE_BRACES,CODE_BRACKETS,CODE_EQUAL,CODE_GREATER_THAN,CODE_GREATER_THAN_OR_EQUAL,CODE_LESS_THAN,CODE_LESS_THAN_OR_EQUAL,CODE_NOT_EQUAL,CODE_NOT_EQUAL_VARIANT,CODE_PARENTHESES,CODE_STRING,CODE_TAGS,CODEPEN,COFFEE,COFFEE_TO_GO,COIN,COLOR_HELPER,COMMENT,COMMENT_ACCOUNT,COMMENT_ACCOUNT_OUTLINE,COMMENT_ALERT,COMMENT_ALERT_OUTLINE,COMMENT_CHECK,COMMENT_CHECK_OUTLINE,COMMENT_MULTIPLE_OUTLINE,COMMENT_OUTLINE,COMMENT_PLUS_OUTLINE,COMMENT_PROCESSING,COMMENT_PROCESSING_OUTLINE,COMMENT_QUESTION_OUTLINE,COMMENT_REMOVE_OUTLINE,COMMENT_TEXT,COMMENT_TEXT_OUTLINE,COMPARE,COMPASS,COMPASS_OUTLINE,CONSOLE,CONTACT_MAIL,CONTENT_COPY,CONTENT_CUT,CONTENT_DUPLICATE,CONTENT_PASTE,CONTENT_SAVE,CONTENT_SAVE_ALL,CONTRAST,CONTRAST_BOX,CONTRAST_CIRCLE,COOKIE,COUNTER,COW,CREDIT_CARD,CREDIT_CARD_MULTIPLE,CREDIT_CARD_SCAN,CROP,CROP_FREE,CROP_LANDSCAPE,CROP_PORTRAIT,CROP_SQUARE,CROSSHAIRS,CROSSHAIRS_GPS,CROWN,CUBE,CUBE_OUTLINE,CUBE_SEND,CUBE_UNFOLDED,CUP,CUP_WATER,CURRENCY_BTC,CURRENCY_EUR,CURRENCY_GBP,CURRENCY_INR,CURRENCY_NGN,CURRENCY_RUB,CURRENCY_TRY,CURRENCY_USD,CURSOR_DEFAULT,CURSOR_DEFAULT_OUTLINE,CURSOR_MOVE,CURSOR_POINTER,DATABASE,DATABASE_MINUS,DATABASE_PLUS,DEBUG_STEP_INTO,DEBUG_STEP_OUT,DEBUG_STEP_OVER,DECIMAL_DECREASE,DECIMAL_INCREASE,DELETE,DELETE_VARIANT,DELTA,DESKPHONE,DESKTOP_MAC,DESKTOP_TOWER,DETAILS,DEVIANTART,DIAMOND,CREATION,DICE_1,DICE_2,DICE_3,DICE_4,DICE_5,DICE_6,DIRECTIONS,DISK_ALERT,DISQUS,DISQUS_OUTLINE,DIVISION,DIVISION_BOX,DNS,DOMAIN,DOTS_HORIZONTAL,DOTS_VERTICAL,DOWNLOAD,DRAG,DRAG_HORIZONTAL,DRAG_VERTICAL,DRAWING,DRAWING_BOX,DRIBBBLE,DRIBBBLE_BOX,DRONE,DROPBOX,DRUPAL,DUCK,DUMBBELL,EARTH,EARTH_OFF,EDGE,EJECT,ELEVATION_DECLINE,ELEVATION_RISE,ELEVATOR,EMAIL,EMAIL_OPEN,EMAIL_OUTLINE,EMAIL_SECURE,EMOTICON,EMOTICON_COOL,EMOTICON_DEVIL,EMOTICON_HAPPY,EMOTICON_NEUTRAL,EMOTICON_POOP,EMOTICON_SAD,EMOTICON_TONGUE,ENGINE,ENGINE_OUTLINE,EQUAL,EQUAL_BOX,ERASER,ESCALATOR,ETHERNET,ETHERNET_CABLE,ETHERNET_CABLE_OFF,ETSY,EVERNOTE,EXCLAMATION,EXIT_TO_APP,EXPORT,EYE,EYE_OFF,EYEDROPPER,EYEDROPPER_VARIANT,FACEBOOK,FACEBOOK_BOX,FACEBOOK_MESSENGER,FACTORY,FAN,FAST_FORWARD,FAX,FERRY,FILE,FILE_CHART,FILE_CHECK,FILE_CLOUD,FILE_DELIMITED,FILE_DOCUMENT,FILE_DOCUMENT_BOX,FILE_EXCEL,FILE_EXCEL_BOX,FILE_EXPORT,FILE_FIND,FILE_IMAGE,FILE_IMPORT,FILE_LOCK,FILE_MULTIPLE,FILE_MUSIC,FILE_OUTLINE,FILE_PDF,FILE_PDF_BOX,FILE_POWERPOINT,FILE_POWERPOINT_BOX,FILE_PRESENTATION_BOX,FILE_SEND,FILE_VIDEO,FILE_WORD,FILE_WORD_BOX,FILE_XML,FILM,FILMSTRIP,FILMSTRIP_OFF,FILTER,FILTER_OUTLINE,FILTER_REMOVE,FILTER_REMOVE_OUTLINE,FILTER_VARIANT,FINGERPRINT,FIRE,FIREFOX,FISH,FLAG,FLAG_CHECKERED,FLAG_OUTLINE,FLAG_OUTLINE_VARIANT,FLAG_TRIANGLE,FLAG_VARIANT,FLASH,FLASH_AUTO,FLASH_OFF,FLASHLIGHT,FLASHLIGHT_OFF,FLATTR,FLIP_TO_BACK,FLIP_TO_FRONT,FLOPPY,FLOWER,FOLDER,FOLDER_ACCOUNT,FOLDER_DOWNLOAD,FOLDER_GOOGLE_DRIVE,FOLDER_IMAGE,FOLDER_LOCK,FOLDER_LOCK_OPEN,FOLDER_MOVE,FOLDER_MULTIPLE,FOLDER_MULTIPLE_IMAGE,FOLDER_MULTIPLE_OUTLINE,FOLDER_OUTLINE,FOLDER_PLUS,FOLDER_REMOVE,FOLDER_UPLOAD,FOOD,FOOD_APPLE,FOOD_VARIANT,FOOTBALL,FOOTBALL_AUSTRALIAN,FOOTBALL_HELMET,FORMAT_ALIGN_CENTER,FORMAT_ALIGN_JUSTIFY,FORMAT_ALIGN_LEFT,FORMAT_ALIGN_RIGHT,FORMAT_BOLD,FORMAT_CLEAR,FORMAT_COLOR_FILL,FORMAT_FLOAT_CENTER,FORMAT_FLOAT_LEFT,FORMAT_FLOAT_NONE,FORMAT_FLOAT_RIGHT,FORMAT_HEADER_1,FORMAT_HEADER_2,FORMAT_HEADER_3,FORMAT_HEADER_4,FORMAT_HEADER_5,FORMAT_HEADER_6,FORMAT_HEADER_DECREASE,FORMAT_HEADER_EQUAL,FORMAT_HEADER_INCREASE,FORMAT_HEADER_POUND,FORMAT_INDENT_DECREASE,FORMAT_INDENT_INCREASE,FORMAT_ITALIC,FORMAT_LINE_SPACING,FORMAT_LIST_BULLETED,FORMAT_LIST_BULLETED_TYPE,FORMAT_LIST_NUMBERS,FORMAT_PAINT,FORMAT_PARAGRAPH,FORMAT_QUOTE_CLOSE,FORMAT_SIZE,FORMAT_STRIKETHROUGH,FORMAT_STRIKETHROUGH_VARIANT,FORMAT_SUBSCRIPT,FORMAT_SUPERSCRIPT,FORMAT_TEXT,FORMAT_TEXTDIRECTION_L_TO_R,FORMAT_TEXTDIRECTION_R_TO_L,FORMAT_UNDERLINE,FORMAT_WRAP_INLINE,FORMAT_WRAP_SQUARE,FORMAT_WRAP_TIGHT,FORMAT_WRAP_TOP_BOTTOM,FORUM,FORWARD,FOURSQUARE,FRIDGE,FRIDGE_FILLED,FRIDGE_FILLED_BOTTOM,FRIDGE_FILLED_TOP,FULLSCREEN,FULLSCREEN_EXIT,FUNCTION,GAMEPAD,GAMEPAD_VARIANT,GAS_STATION,GATE,GAUGE,GAVEL,GENDER_FEMALE,GENDER_MALE,GENDER_MALE_FEMALE,GENDER_TRANSGENDER,GHOST,GIFT,GIT,GITHUB_BOX,GITHUB_CIRCLE,GLASS_FLUTE,GLASS_MUG,GLASS_STANGE,GLASS_TULIP,GLASSDOOR,GLASSES,GMAIL,GNOME,GOOGLE,GOOGLE_CARDBOARD,GOOGLE_CHROME,GOOGLE_CIRCLES,GOOGLE_CIRCLES_COMMUNITIES,GOOGLE_CIRCLES_EXTENDED,GOOGLE_CIRCLES_GROUP,GOOGLE_CONTROLLER,GOOGLE_CONTROLLER_OFF,GOOGLE_DRIVE,GOOGLE_EARTH,GOOGLE_GLASS,GOOGLE_NEARBY,GOOGLE_PAGES,GOOGLE_PHYSICAL_WEB,GOOGLE_PLAY,GOOGLE_PLUS,GOOGLE_PLUS_BOX,GOOGLE_TRANSLATE,GOOGLE_WALLET,GRID,GRID_OFF,GROUP,GUITAR_ELECTRIC,GUITAR_PICK,GUITAR_PICK_OUTLINE,HAND_POINTING_RIGHT,HANGER,HANGOUTS,HARDDISK,HEADPHONES,HEADPHONES_BOX,HEADPHONES_SETTINGS,HEADSET,HEADSET_DOCK,HEADSET_OFF,HEART,HEART_BOX,HEART_BOX_OUTLINE,HEART_BROKEN,HEART_OUTLINE,HELP,HELP_CIRCLE,HEXAGON,HEXAGON_OUTLINE,HISTORY,HOLOLENS,HOME,HOME_MODERN,HOME_VARIANT,HOPS,HOSPITAL,HOSPITAL_BUILDING,HOSPITAL_MARKER,HOTEL,HOUZZ,HOUZZ_BOX,HUMAN,HUMAN_CHILD,HUMAN_MALE_FEMALE,IMAGE,IMAGE_ALBUM,IMAGE_AREA,IMAGE_AREA_CLOSE,IMAGE_BROKEN,IMAGE_BROKEN_VARIANT,IMAGE_FILTER,IMAGE_FILTER_BLACK_WHITE,IMAGE_FILTER_CENTER_FOCUS,IMAGE_FILTER_CENTER_FOCUS_WEAK,IMAGE_FILTER_DRAMA,IMAGE_FILTER_FRAMES,IMAGE_FILTER_HDR,IMAGE_FILTER_NONE,IMAGE_FILTER_TILT_SHIFT,IMAGE_FILTER_VINTAGE,IMAGE_MULTIPLE,IMPORT_ICON,INBOX_ARROW_DOWN,INFORMATION,INFORMATION_OUTLINE,INSTAGRAM,INSTAPAPER,INTERNET_EXPLORER,INVERT_COLORS,JEEPNEY,JIRA,JSFIDDLE,KEG,KEY,KEY_CHANGE,KEY_MINUS,KEY_PLUS,KEY_REMOVE,KEY_VARIANT,KEYBOARD,KEYBOARD_BACKSPACE,KEYBOARD_CAPS,KEYBOARD_CLOSE,KEYBOARD_OFF,KEYBOARD_RETURN,KEYBOARD_TAB,KEYBOARD_VARIANT,KODI,LABEL,LABEL_OUTLINE,LAN,LAN_CONNECT,LAN_DISCONNECT,LAN_PENDING,LANGUAGE_CSHARP,LANGUAGE_CSS3,LANGUAGE_HTML5,LANGUAGE_JAVASCRIPT,LANGUAGE_PHP,LANGUAGE_PYTHON,LANGUAGE_PYTHON_TEXT,LAPTOP,LAPTOP_CHROMEBOOK,LAPTOP_MAC,LAPTOP_WINDOWS,LASTFM,LAUNCH,LAYERS,LAYERS_OFF,LEAF,LED_OFF,LED_ON,LED_OUTLINE,LED_VARIANT_OFF,LED_VARIANT_ON,LED_VARIANT_OUTLINE,LIBRARY,LIBRARY_BOOKS,LIBRARY_MUSIC,LIBRARY_PLUS,LIGHTBULB,LIGHTBULB_OUTLINE,LINK,LINK_OFF,LINK_VARIANT,LINK_VARIANT_OFF,LINKEDIN,LINKEDIN_BOX,LINUX,LOCK,LOCK_OPEN,LOCK_OPEN_OUTLINE,LOCK_OUTLINE,LOGIN,LOGOUT,LOOKS,LOUPE,LUMX,MAGNET,MAGNET_ON,MAGNIFY,MAGNIFY_MINUS,MAGNIFY_PLUS,MAIL_RU,MAP,MAP_MARKER,MAP_MARKER_CIRCLE,MAP_MARKER_MULTIPLE,MAP_MARKER_OFF,MAP_MARKER_RADIUS,MARGIN,MARKDOWN,MARKER_CHECK,MARTINI,MATERIAL_UI,MATH_COMPASS,MAXCDN,MEDIUM,MEMORY,MENU,MENU_DOWN,MENU_LEFT,MENU_RIGHT,MENU_UP,MESSAGE,MESSAGE_ALERT,MESSAGE_DRAW,MESSAGE_IMAGE,MESSAGE_OUTLINE,MESSAGE_PROCESSING,MESSAGE_REPLY,MESSAGE_REPLY_TEXT,MESSAGE_TEXT,MESSAGE_TEXT_OUTLINE,MESSAGE_VIDEO,MICROPHONE,MICROPHONE_OFF,MICROPHONE_OUTLINE,MICROPHONE_SETTINGS,MICROPHONE_VARIANT,MICROPHONE_VARIANT_OFF,MICROSOFT,MINECRAFT,MINUS,MINUS_BOX,MINUS_CIRCLE,MINUS_CIRCLE_OUTLINE,MINUS_NETWORK,MONITOR,MONITOR_MULTIPLE,MORE,MOTORBIKE,MOUSE,MOUSE_OFF,MOUSE_VARIANT,MOUSE_VARIANT_OFF,MOVIE,MULTIPLICATION,MULTIPLICATION_BOX,MUSIC_BOX,MUSIC_BOX_OUTLINE,MUSIC_CIRCLE,MUSIC_NOTE,MUSIC_NOTE_EIGHTH,MUSIC_NOTE_HALF,MUSIC_NOTE_OFF,MUSIC_NOTE_QUARTER,MUSIC_NOTE_SIXTEENTH,MUSIC_NOTE_WHOLE,NATURE,NATURE_PEOPLE,NAVIGATION,NEEDLE,NEST_PROTECT,NEST_THERMOSTAT,NEW_BOX,NEWSPAPER,NFC,NFC_TAP,NFC_VARIANT,NODEJS,NOTE,NOTE_OUTLINE,NOTE_PLUS,NOTE_PLUS_OUTLINE,NOTE_TEXT,NOTIFICATION_CLEAR_ALL,NUMERIC,NUMERIC_0_BOX,NUMERIC_0_BOX_MULTIPLE_OUTLINE,NUMERIC_0_BOX_OUTLINE,NUMERIC_1_BOX,NUMERIC_1_BOX_MULTIPLE_OUTLINE,NUMERIC_1_BOX_OUTLINE,NUMERIC_2_BOX,NUMERIC_2_BOX_MULTIPLE_OUTLINE,NUMERIC_2_BOX_OUTLINE,NUMERIC_3_BOX,NUMERIC_3_BOX_MULTIPLE_OUTLINE,NUMERIC_3_BOX_OUTLINE,NUMERIC_4_BOX,NUMERIC_4_BOX_MULTIPLE_OUTLINE,NUMERIC_4_BOX_OUTLINE,NUMERIC_5_BOX,NUMERIC_5_BOX_MULTIPLE_OUTLINE,NUMERIC_5_BOX_OUTLINE,NUMERIC_6_BOX,NUMERIC_6_BOX_MULTIPLE_OUTLINE,NUMERIC_6_BOX_OUTLINE,NUMERIC_7_BOX,NUMERIC_7_BOX_MULTIPLE_OUTLINE,NUMERIC_7_BOX_OUTLINE,NUMERIC_8_BOX,NUMERIC_8_BOX_MULTIPLE_OUTLINE,NUMERIC_8_BOX_OUTLINE,NUMERIC_9_BOX,NUMERIC_9_BOX_MULTIPLE_OUTLINE,NUMERIC_9_BOX_OUTLINE,NUMERIC_9_PLUS_BOX,NUMERIC_9_PLUS_BOX_MULTIPLE_OUTLINE,NUMERIC_9_PLUS_BOX_OUTLINE,NUTRITION,OCTAGON,OCTAGON_OUTLINE,ODNOKLASSNIKI,OFFICE,OIL,OIL_TEMPERATURE,OMEGA,ONEDRIVE,OPEN_IN_APP,OPEN_IN_NEW,OPENID,OPERA,ORNAMENT,ORNAMENT_VARIANT,INBOX_ARROW_UP,OWL,PACKAGE_ICON,PACKAGE_DOWN,PACKAGE_UP,PACKAGE_VARIANT,PACKAGE_VARIANT_CLOSED,PALETTE,PALETTE_ADVANCED,PANDA,PANDORA,PANORAMA,PANORAMA_FISHEYE,PANORAMA_HORIZONTAL,PANORAMA_VERTICAL,PANORAMA_WIDE_ANGLE,PAPER_CUT_VERTICAL,PAPERCLIP,PARKING,PAUSE,PAUSE_CIRCLE,PAUSE_CIRCLE_OUTLINE,PAUSE_OCTAGON,PAUSE_OCTAGON_OUTLINE,PAW,PEN,PENCIL,PENCIL_BOX,PENCIL_BOX_OUTLINE,PENCIL_LOCK,PENCIL_OFF,PERCENT,PHARMACY,PHONE,PHONE_BLUETOOTH,PHONE_FORWARD,PHONE_HANGUP,PHONE_IN_TALK,PHONE_INCOMING,PHONE_LOCKED,PHONE_LOG,PHONE_MISSED,PHONE_OUTGOING,PHONE_PAUSED,PHONE_SETTINGS,PHONE_VOIP,PI,PI_BOX,PIG,PILL,PIN,PIN_OFF,PINE_TREE,PINE_TREE_BOX,PINTEREST,PINTEREST_BOX,PIZZA,PLAY,PLAY_BOX_OUTLINE,PLAY_CIRCLE,PLAY_CIRCLE_OUTLINE,PLAY_PAUSE,PLAY_PROTECTED_CONTENT,PLAYLIST_MINUS,PLAYLIST_PLAY,PLAYLIST_PLUS,PLAYLIST_REMOVE,PLAYSTATION,PLUS,PLUS_BOX,PLUS_CIRCLE,PLUS_CIRCLE_MULTIPLE_OUTLINE,PLUS_CIRCLE_OUTLINE,PLUS_NETWORK,PLUS_ONE,POCKET,POKEBALL,POLAROID,POLL,POLL_BOX,POLYMER,POPCORN,POUND,POUND_BOX,POWER,POWER_SETTINGS,POWER_SOCKET,PRESENTATION,PRESENTATION_PLAY,PRINTER,PRINTER_3D,PRINTER_ALERT,PROFESSIONAL_HEXAGON,PROJECTOR,PROJECTOR_SCREEN,PULSE,PUZZLE,QRCODE,QRCODE_SCAN,QUADCOPTER,QUALITY_HIGH,QUICKTIME,RADAR,RADIATOR,RADIO,RADIO_HANDHELD,RADIO_TOWER,RADIOACTIVE,RADIOBOX_BLANK,RADIOBOX_MARKED,RASPBERRYPI,RAY_END,RAY_END_ARROW,RAY_START,RAY_START_ARROW,RAY_START_END,RAY_VERTEX,RDIO,READ,READABILITY,RECEIPT,RECORD,RECORD_REC,RECYCLE,REDDIT,REDO,REDO_VARIANT,REFRESH,REGEX,RELATIVE_SCALE,RELOAD,REMOTE,RENAME_BOX,REPEAT,REPEAT_OFF,REPEAT_ONCE,REPLAY,REPLY,REPLY_ALL,REPRODUCTION,RESIZE_BOTTOM_RIGHT,RESPONSIVE,REWIND,RIBBON,ROAD,ROAD_VARIANT,ROCKET,ROTATE_3D,ROTATE_LEFT,ROTATE_LEFT_VARIANT,ROTATE_RIGHT,ROTATE_RIGHT_VARIANT,ROUTER_WIRELESS,ROUTES,RSS,RSS_BOX,RULER,RUN_FAST,SALE,SATELLITE,SATELLITE_VARIANT,SCALE,SCALE_BATHROOM,SCHOOL,SCREEN_ROTATION,SCREEN_ROTATION_LOCK,SCREWDRIVER,SCRIPT,SD,SEAL,SEAT_FLAT,SEAT_FLAT_ANGLED,SEAT_INDIVIDUAL_SUITE,SEAT_LEGROOM_EXTRA,SEAT_LEGROOM_NORMAL,SEAT_LEGROOM_REDUCED,SEAT_RECLINE_EXTRA,SEAT_RECLINE_NORMAL,SECURITY,SECURITY_NETWORK,SELECT,SELECT_ALL,SELECT_INVERSE,SELECT_OFF,SELECTION,SEND,SERVER,SERVER_MINUS,SERVER_NETWORK,SERVER_NETWORK_OFF,SERVER_OFF,SERVER_PLUS,SERVER_REMOVE,SERVER_SECURITY,SETTINGS,SETTINGS_BOX,SHAPE_PLUS,SHARE,SHARE_VARIANT,SHIELD,SHIELD_OUTLINE,SHOPPING,SHOPPING_MUSIC,SHREDDER,SHUFFLE,SHUFFLE_DISABLED,SHUFFLE_VARIANT,SIGMA,SIGN_CAUTION,SIGNAL,SILVERWARE,SILVERWARE_FORK,SILVERWARE_SPOON,SILVERWARE_VARIANT,SIM,SIM_ALERT,SIM_OFF,SITEMAP,SKIP_BACKWARD,SKIP_FORWARD,SKIP_NEXT,SKIP_PREVIOUS,SKYPE,SKYPE_BUSINESS,SLACK,SLEEP,SLEEP_OFF,SMOKING,SMOKING_OFF,SNAPCHAT,SNOWMAN,SOCCER,SOFA,SORT,SORT_ALPHABETICAL,SORT_ASCENDING,SORT_DESCENDING,SORT_NUMERIC,SORT_VARIANT,SOUNDCLOUD,SOURCE_FORK,SOURCE_PULL,SPEAKER,SPEAKER_OFF,SPEEDOMETER,SPELLCHECK,SPOTIFY,SPOTLIGHT,SPOTLIGHT_BEAM,SQUARE_INC,SQUARE_INC_CASH,STACKOVERFLOW,STAIRS,STAR,STAR_CIRCLE,STAR_HALF,STAR_OFF,STAR_OUTLINE,STEAM,STEERING,STEP_BACKWARD,STEP_BACKWARD_2,STEP_FORWARD,STEP_FORWARD_2,STETHOSCOPE,STOCKING,STOP,STORE,STORE_24_HOUR,STOVE,SUBWAY_VARIANT,SUNGLASSES,SWAP_HORIZONTAL,SWAP_VERTICAL,SWIM,SWITCH_ICON,SWORD,SYNC,SYNC_ALERT,SYNC_OFF,TAB,TAB_UNSELECTED,TABLE,TABLE_COLUMN_PLUS_AFTER,TABLE_COLUMN_PLUS_BEFORE,TABLE_COLUMN_REMOVE,TABLE_COLUMN_WIDTH,TABLE_EDIT,TABLE_LARGE,TABLE_ROW_HEIGHT,TABLE_ROW_PLUS_AFTER,TABLE_ROW_PLUS_BEFORE,TABLE_ROW_REMOVE,TABLET,TABLET_ANDROID,TABLET_IPAD,TAG,TAG_FACES,TAG_MULTIPLE,TAG_OUTLINE,TAG_TEXT_OUTLINE,TARGET,TAXI,TEAMVIEWER,TELEGRAM,TELEVISION,TELEVISION_GUIDE,TEMPERATURE_CELSIUS,TEMPERATURE_FAHRENHEIT,TEMPERATURE_KELVIN,TENNIS,TENT,TERRAIN,TEXT_TO_SPEECH,TEXT_TO_SPEECH_OFF,TEXTURE,THEATER,THEME_LIGHT_DARK,THERMOMETER,THERMOMETER_LINES,THUMB_DOWN,THUMB_DOWN_OUTLINE,THUMB_UP,THUMB_UP_OUTLINE,THUMBS_UP_DOWN,TICKET,TICKET_ACCOUNT,TICKET_CONFIRMATION,TIE,TIMELAPSE,TIMER,TIMER_10,TIMER_3,TIMER_OFF,TIMER_SAND,TIMETABLE,TOGGLE_SWITCH,TOGGLE_SWITCH_OFF,TOOLTIP,TOOLTIP_EDIT,TOOLTIP_IMAGE,TOOLTIP_OUTLINE,TOOLTIP_OUTLINE_PLUS,TOOLTIP_TEXT,TOOTH,TOR,TRAFFIC_LIGHT,TRAIN,TRAM,TRANSCRIBE,TRANSCRIBE_CLOSE,TRANSFER,TREE,TRELLO,TRENDING_DOWN,TRENDING_NEUTRAL,TRENDING_UP,TRIANGLE,TRIANGLE_OUTLINE,TROPHY,TROPHY_AWARD,TROPHY_OUTLINE,TROPHY_VARIANT,TROPHY_VARIANT_OUTLINE,TRUCK,TRUCK_DELIVERY,TSHIRT_CREW,TSHIRT_V,TUMBLR,TUMBLR_REBLOG,TWITCH,TWITTER,TWITTER_BOX,TWITTER_CIRCLE,TWITTER_RETWEET,UBUNTU,UMBRACO,UMBRELLA,UMBRELLA_OUTLINE,UNDO,UNDO_VARIANT,UNFOLD_LESS_HORIZONTAL,UNFOLD_MORE_HORIZONTAL,UNGROUP,UNTAPPD,UPLOAD,USB,VECTOR_ARRANGE_ABOVE,VECTOR_ARRANGE_BELOW,VECTOR_CIRCLE,VECTOR_CIRCLE_VARIANT,VECTOR_COMBINE,VECTOR_CURVE,VECTOR_DIFFERENCE,VECTOR_DIFFERENCE_AB,VECTOR_DIFFERENCE_BA,VECTOR_INTERSECTION,VECTOR_LINE,VECTOR_POINT,VECTOR_POLYGON,VECTOR_POLYLINE,VECTOR_SELECTION,VECTOR_TRIANGLE,VECTOR_UNION,VERIFIED,VIBRATE,VIDEO,VIDEO_OFF,VIDEO_SWITCH,VIEW_AGENDA,VIEW_ARRAY,VIEW_CAROUSEL,VIEW_COLUMN,VIEW_DASHBOARD,VIEW_DAY,VIEW_GRID,VIEW_HEADLINE,VIEW_LIST,VIEW_MODULE,VIEW_QUILT,VIEW_STREAM,VIEW_WEEK,VIMEO,VINE,VK,VK_BOX,VK_CIRCLE,VLC,VOICEMAIL,VOLUME_HIGH,VOLUME_LOW,VOLUME_MEDIUM,VOLUME_OFF,VPN,WALK,WALLET,WALLET_GIFTCARD,WALLET_MEMBERSHIP,WALLET_TRAVEL,WAN,WATCH,WATCH_EXPORT,WATCH_IMPORT,WATER,WATER_OFF,WATER_PERCENT,WATER_PUMP,WEATHER_CLOUDY,WEATHER_FOG,WEATHER_HAIL,WEATHER_LIGHTNING,WEATHER_NIGHT,WEATHER_PARTLYCLOUDY,WEATHER_POURING,WEATHER_RAINY,WEATHER_SNOWY,WEATHER_SUNNY,WEATHER_SUNSET,WEATHER_SUNSET_DOWN,WEATHER_SUNSET_UP,WEATHER_WINDY,WEATHER_WINDY_VARIANT,WEB,WEBCAM,WEIGHT,WEIGHT_KILOGRAM,WHATSAPP,WHEELCHAIR_ACCESSIBILITY,WHITE_BALANCE_AUTO,WHITE_BALANCE_INCANDESCENT,WHITE_BALANCE_IRIDESCENT,WHITE_BALANCE_SUNNY,WIFI,WIFI_OFF,WII,WIKIPEDIA,WINDOW_CLOSE,WINDOW_CLOSED,WINDOW_MAXIMIZE,WINDOW_MINIMIZE,WINDOW_OPEN,WINDOW_RESTORE,WINDOWS,WORDPRESS,WORKER,WRAP,WRENCH,WUNDERLIST,XBOX,XBOX_CONTROLLER,XBOX_CONTROLLER_OFF,XDA,XING,XING_BOX,XING_CIRCLE,XML,YEAST,YELP,YOUTUBE_PLAY,ZIP_BOX,SURROUND_SOUND,VECTOR_RECTANGLE,PLAYLIST_CHECK,FORMAT_LINE_STYLE,FORMAT_LINE_WEIGHT,TRANSLATE,VOICE,OPACITY,NEAR_ME,CLOCK_ALERT,HUMAN_PREGNANT,STICKER,SCALE_BALANCE,ACCOUNT_CARD_DETAILS,ACCOUNT_MULTIPLE_MINUS,AIRPLANE_LANDING,AIRPLANE_TAKEOFF,ALERT_CIRCLE_OUTLINE,ALTIMETER,ANIMATION,BOOK_MINUS,BOOK_OPEN_PAGE_VARIANT,BOOK_PLUS,BOOMBOX,BULLSEYE,BURST_MODE,CAMERA_OFF,CHECK_CIRCLE,CHECK_CIRCLE_OUTLINE,CANDLE,CHART_BUBBLE,CREDIT_CARD_OFF,CUP_OFF,COPYRIGHT,CURSOR_TEXT,DELETE_FOREVER,DELETE_SWEEP,DICE_D20,DICE_D4,DICE_D6,DICE_D8,DISK,EMAIL_OPEN_OUTLINE,EMAIL_VARIANT,EV_STATION,FOOD_FORK_DRINK,FOOD_OFF,FORMAT_TITLE,GOOGLE_MAPS,HEART_PULSE,HIGHWAY,HOME_MAP_MARKER,INCOGNITO,KETTLE,LOCK_PLUS,LOGIN_VARIANT,LOGOUT_VARIANT,MUSIC_NOTE_BLUETOOTH,MUSIC_NOTE_BLUETOOTH_OFF,PAGE_FIRST,PAGE_LAST,PHONE_CLASSIC,PRIORITY_HIGH,PRIORITY_LOW,QQCHAT,POOL,ROUNDED_CORNER,ROWING,SAXOPHONE,SIGNAL_VARIANT,STACKEXCHANGE,SUBDIRECTORY_ARROW_LEFT,SUBDIRECTORY_ARROW_RIGHT,TEXTBOX,VIOLIN,VISUALSTUDIO,WECHAT,WATERMARK,FILE_HIDDEN,APPLICATION,ARROW_COLLAPSE,ARROW_EXPAND,BOWL,BRIDGE,BUFFER,CHIP,CONTENT_SAVE_SETTINGS,DIALPAD,DICTIONARY,FORMAT_HORIZONTAL_ALIGN_CENTER,FORMAT_HORIZONTAL_ALIGN_LEFT,FORMAT_HORIZONTAL_ALIGN_RIGHT,FORMAT_VERTICAL_ALIGN_BOTTOM,FORMAT_VERTICAL_ALIGN_CENTER,FORMAT_VERTICAL_ALIGN_TOP,HACKERNEWS,HELP_CIRCLE_OUTLINE,JSON,LAMBDA,MATRIX,METEOR,MIXCLOUD,SIGMA_LOWER,SOURCE_BRANCH,SOURCE_MERGE,TUNE,WEBHOOK,ACCOUNT_SETTINGS,ACCOUNT_SETTINGS_VARIANT,APPLE_KEYBOARD_CAPS,APPLE_KEYBOARD_COMMAND,APPLE_KEYBOARD_CONTROL,APPLE_KEYBOARD_OPTION,APPLE_KEYBOARD_SHIFT,BOX_SHADOW,CARDS,CARDS_OUTLINE,CARDS_PLAYING_OUTLINE,CHECKBOX_MULTIPLE_BLANK_CIRCLE,CHECKBOX_MULTIPLE_BLANK_CIRCLE_OUTLINE,CHECKBOX_MULTIPLE_MARKED_CIRCLE,CHECKBOX_MULTIPLE_MARKED_CIRCLE_OUTLINE,CLOUD_SYNC,COLLAGE,DIRECTIONS_FORK,ERASER_VARIANT,FACE,FACE_PROFILE,FILE_TREE,FORMAT_ANNOTATION_PLUS,GAS_CYLINDER,GREASE_PENCIL,HUMAN_FEMALE,HUMAN_GREETING,HUMAN_HANDSDOWN,HUMAN_HANDSUP,HUMAN_MALE,INFORMATION_VARIANT,LEAD_PENCIL,MAP_MARKER_MINUS,MAP_MARKER_PLUS,MARKER,MESSAGE_PLUS,MICROSCOPE,MOVE_RESIZE,MOVE_RESIZE_VARIANT,PAW_OFF,PHONE_MINUS,PHONE_PLUS,POT,POT_MIX,SERIAL_PORT,SHAPE_CIRCLE_PLUS,SHAPE_POLYGON_PLUS,SHAPE_RECTANGLE_PLUS,SHAPE_SQUARE_PLUS,SKIP_NEXT_CIRCLE,SKIP_NEXT_CIRCLE_OUTLINE,SKIP_PREVIOUS_CIRCLE,SKIP_PREVIOUS_CIRCLE_OUTLINE,SPRAY,STOP_CIRCLE,STOP_CIRCLE_OUTLINE,TEST_TUBE,TEXT_SHADOW,TUNE_VERTICAL,CART_OFF,CHART_GANTT,CHART_SCATTERPLOT_HEXBIN,CHART_TIMELINE,DISCORD,FILE_RESTORE,LANGUAGE_C,LANGUAGE_CPP,XAML,BANDCAMP,CREDIT_CARD_PLUS,ITUNES,BOW_TIE,CALENDAR_RANGE,CURRENCY_USD_OFF,FLASH_RED_EYE,OAR,PIANO,WEATHER_LIGHTNING_RAINY,WEATHER_SNOWY_RAINY,YIN_YANG,TOWER_BEACH,TOWER_FIRE,DELETE_CIRCLE,DNA,HAMBURGER,GONDOLA,INBOX,REORDER_HORIZONTAL,REORDER_VERTICAL,SECURITY_HOME,TAG_HEART,SKULL,SOLID,ALARM_SNOOZE,BABY_BUGGY,BEAKER,BOMB,CALENDAR_QUESTION,CAMERA_BURST,CODE_TAGS_CHECK,COINS,CROP_ROTATE,DEVELOPER_BOARD,DO_NOT_DISTURB,DO_NOT_DISTURB_OFF,DOUBAN,EMOTICON_DEAD,EMOTICON_EXCITED,FOLDER_STAR,FORMAT_COLOR_TEXT,FORMAT_SECTION,GRADIENT,HOME_OUTLINE,MESSAGE_BULLETED,MESSAGE_BULLETED_OFF,NUKE,POWER_PLUG,POWER_PLUG_OFF,PUBLISH,RESTORE,ROBOT,FORMAT_ROTATE_90,SCANNER,SUBWAY,TIMER_SAND_EMPTY,TRANSIT_TRANSFER,UNITY,UPDATE,WATCH_VIBRATE,ANGULAR,DOLBY,EMBY,LAMP,MENU_DOWN_OUTLINE,MENU_UP_OUTLINE,NOTE_MULTIPLE,NOTE_MULTIPLE_OUTLINE,PLEX,PLANE_SHIELD,ACCOUNT_EDIT,ALERT_DECAGRAM,ALL_INCLUSIVE,ANGULARJS,ARROW_DOWN_BOX,ARROW_LEFT_BOX,ARROW_RIGHT_BOX,ARROW_UP_BOX,ASTERISK,BOMB_OFF,BOOTSTRAP,CARDS_VARIANT,CLIPBOARD_FLOW,CLOSE_OUTLINE,COFFEE_OUTLINE,CONTACTS,DELETE_EMPTY,EARTH_BOX,EARTH_BOX_OFF,EMAIL_ALERT,EYE_OUTLINE,EYE_OFF_OUTLINE,FAST_FORWARD_OUTLINE,FEATHER,FIND_REPLACE,FLASH_OUTLINE,FORMAT_FONT,FORMAT_PAGE_BREAK,FORMAT_PILCROW,GARAGE,GARAGE_OPEN,GITHUB_FACE,GOOGLE_KEEP,GOOGLE_PHOTOS,HEART_HALF_FULL,HEART_HALF,HEART_HALF_OUTLINE,HEXAGON_MULTIPLE,HOOK,HOOK_OFF,INFINITY,LANGUAGE_SWIFT,LANGUAGE_TYPESCRIPT,LAPTOP_OFF,LIGHTBULB_ON,LIGHTBULB_ON_OUTLINE,LOCK_PATTERN,LOOP,MAGNIFY_MINUS_OUTLINE,MAGNIFY_PLUS_OUTLINE,MAILBOX,MEDICAL_BAG,MESSAGE_SETTINGS,MESSAGE_SETTINGS_VARIANT,MINUS_BOX_OUTLINE,NETWORK,DOWNLOAD_NETWORK,HELP_NETWORK,UPLOAD_NETWORK,NPM,NUT,OCTAGRAM,PAGE_LAYOUT_BODY,PAGE_LAYOUT_FOOTER,PAGE_LAYOUT_HEADER,PAGE_LAYOUT_SIDEBAR_LEFT,PAGE_LAYOUT_SIDEBAR_RIGHT,PENCIL_CIRCLE,PENTAGON,PENTAGON_OUTLINE,PILLAR,PISTOL,PLUS_BOX_OUTLINE,PLUS_OUTLINE,PRESCRIPTION,PRINTER_SETTINGS,REACT,RESTART,REWIND_OUTLINE,RHOMBUS,RHOMBUS_OUTLINE,ROOMBA,RUN,SEARCH_WEB,SHOVEL,SHOVEL_OFF,SIGNAL_2G,SIGNAL_3G,SIGNAL_4G,SIGNAL_HSPA,SIGNAL_HSPA_PLUS,SNOWFLAKE,SOURCE_COMMIT,SOURCE_COMMIT_END,SOURCE_COMMIT_END_LOCAL,SOURCE_COMMIT_LOCAL,SOURCE_COMMIT_NEXT_LOCAL,SOURCE_COMMIT_START,SOURCE_COMMIT_START_NEXT_LOCAL,SPEAKER_WIRELESS,STADIUM,SVG,TAG_PLUS,TAG_REMOVE,TICKET_PERCENT,TILDE,TREASURE_CHEST,TRUCK_TRAILER,VIEW_PARALLEL,VIEW_SEQUENTIAL,WASHING_MACHINE,WEBPACK,WIDGETS,WIIU,ARROW_DOWN_BOLD,ARROW_DOWN_BOLD_BOX,ARROW_DOWN_BOLD_BOX_OUTLINE,ARROW_LEFT_BOLD,ARROW_LEFT_BOLD_BOX,ARROW_LEFT_BOLD_BOX_OUTLINE,ARROW_RIGHT_BOLD,ARROW_RIGHT_BOLD_BOX,ARROW_RIGHT_BOLD_BOX_OUTLINE,ARROW_UP_BOLD,ARROW_UP_BOLD_BOX,ARROW_UP_BOLD_BOX_OUTLINE,CANCEL,FILE_ACCOUNT,GESTURE_DOUBLE_TAP,GESTURE_SWIPE_DOWN,GESTURE_SWIPE_LEFT,GESTURE_SWIPE_RIGHT,GESTURE_SWIPE_UP,GESTURE_TAP,GESTURE_TWO_DOUBLE_TAP,GESTURE_TWO_TAP,HUMBLE_BUNDLE,KICKSTARTER,NETFLIX,ONENOTE,PERISCOPE,UBER,VECTOR_RADIUS,XBOX_CONTROLLER_BATTERY_ALERT,XBOX_CONTROLLER_BATTERY_EMPTY,XBOX_CONTROLLER_BATTERY_FULL,XBOX_CONTROLLER_BATTERY_LOW,XBOX_CONTROLLER_BATTERY_MEDIUM,XBOX_CONTROLLER_BATTERY_UNKNOWN,CLIPBOARD_PLUS,FILE_PLUS,FORMAT_ALIGN_BOTTOM,FORMAT_ALIGN_MIDDLE,FORMAT_ALIGN_TOP,FORMAT_LIST_CHECKS,FORMAT_QUOTE_OPEN,GRID_LARGE,HEART_OFF,MUSIC,MUSIC_OFF,TAB_PLUS,VOLUME_PLUS,VOLUME_MINUS,VOLUME_MUTE,UNFOLD_LESS_VERTICAL,UNFOLD_MORE_VERTICAL,TACO,SQUARE_OUTLINE,SQUARE,CIRCLE,CIRCLE_OUTLINE,ALERT_OCTAGRAM,ATOM,CEILING_LIGHT,CHART_BAR_STACKED,CHART_LINE_STACKED,DECAGRAM,DECAGRAM_OUTLINE,DICE_MULTIPLE,DICE_D10,FOLDER_OPEN,GUITAR_ACOUSTIC,LOADING,LOCK_RESET,NINJA,OCTAGRAM_OUTLINE,PENCIL_CIRCLE_OUTLINE,SELECTION_OFF,SET_ALL,SET_CENTER,SET_CENTER_RIGHT,SET_LEFT,SET_LEFT_CENTER,SET_LEFT_RIGHT,SET_NONE,SET_RIGHT,SHIELD_HALF_FULL,SIGN_DIRECTION,SIGN_TEXT,SIGNAL_OFF,SQUARE_ROOT,STICKER_EMOJI,SUMMIT,SWORD_CROSS,TRUCK_FAST,YAMMER,CAST_OFF,HELP_BOX,TIMER_SAND_FULL,WAVES,ALARM_BELL,ALARM_LIGHT,ANDROID_HEAD,APPROVAL,ARROW_COLLAPSE_DOWN,ARROW_COLLAPSE_LEFT,ARROW_COLLAPSE_RIGHT,ARROW_COLLAPSE_UP,ARROW_EXPAND_DOWN,ARROW_EXPAND_LEFT,ARROW_EXPAND_RIGHT,ARROW_EXPAND_UP,BOOK_SECURE,BOOK_UNSECURE,BUS_ARTICULATED_END,BUS_ARTICULATED_FRONT,BUS_DOUBLE_DECKER,BUS_SCHOOL,BUS_SIDE,CAMERA_GOPRO,CAMERA_METERING_CENTER,CAMERA_METERING_MATRIX,CAMERA_METERING_PARTIAL,CAMERA_METERING_SPOT,CANNABIS,CAR_CONVERTABLE,CAR_ESTATE,CAR_HATCHBACK,CAR_PICKUP,CAR_SIDE,CAR_SPORTS,CARAVAN,CCTV,CHART_DONUT,CHART_DONUT_VARIANT,CHART_LINE_VARIANT,CHILI_HOT,CHILI_MEDIUM,CHILI_MILD,CLOUD_BRACES,CLOUD_TAGS,CONSOLE_LINE,CORN,CURRENCY_CHF,CURRENCY_CNY,CURRENCY_ETH,CURRENCY_JPY,CURRENCY_KRW,CURRENCY_SIGN,CURRENCY_TWD,DESKTOP_CLASSIC,DIP_SWITCH,DONKEY,DOTS_HORIZONTAL_CIRCLE,DOTS_VERTICAL_CIRCLE,EAR_HEARING,ELEPHANT,EVENTBRITE,FOOD_CROISSANT,FORKLIFT,FUEL,GESTURE,GOOGLE_ANALYTICS,GOOGLE_ASSISTANT,HEADPHONES_OFF,HIGH_DEFINITION,HOME_ASSISTANT,HOME_AUTOMATION,HOME_CIRCLE,LANGUAGE_GO,LANGUAGE_R,LAVA_LAMP,LED_STRIP,LOCKER,LOCKER_MULTIPLE,MAP_MARKER_OUTLINE,METRONOME,METRONOME_TICK,MICRO_SD,MIXER,MOVIE_ROLL,MUSHROOM,MUSHROOM_OUTLINE,NINTENDO_SWITCH,NULL,PASSPORT,PERIODIC_TABLE_CO2,PIPE,PIPE_DISCONNECTED,POWER_SOCKET_EU,POWER_SOCKET_UK,POWER_SOCKET_US,RICE,RING,SASS,SEND_SECURE,SOY_SAUCE,STANDARD_DEFINITION,SURROUND_SOUND_2_0,SURROUND_SOUND_3_1,SURROUND_SOUND_5_1,SURROUND_SOUND_7_1,TELEVISION_CLASSIC,TEXTBOX_PASSWORD,THOUGHT_BUBBLE,THOUGHT_BUBBLE_OUTLINE,TRACKPAD,ULTRA_HIGH_DEFINITION,VAN_PASSENGER,VAN_UTILITY,VANISH,VIDEO_3D,WALL,XMPP};

    public static final int ANDROID_ACTIONBAR_ICON_SIZE_DP = 24;

    private final Context context;

    private IconValue icon = null;

    private TextPaint paint;

    private int size = -1;

    private int alpha = 255;

    private final Rect bounds = new Rect();

    /**
     * Create an IconDrawable.
     * @param context Your activity or application context.
     */
    private MaterialDrawableBuilder(Context context) {
        this.context = context;
        //this.icon = icon;
        paint = new TextPaint();
        paint.setTypeface(MaterialIconUtils.getTypeFace(context));
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setUnderlineText(false);
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);

        setToActionbarSize();
        setColor(Color.BLACK);
    }

    public static MaterialDrawableBuilder with(Context context){
        return new MaterialDrawableBuilder(context);
    }

    public Drawable build() throws IconNotSetException{
        if(icon == null){
            throw new IconNotSetException();
        }
        return new MaterialDrawable(icon, paint, size, alpha);
    }

    public MaterialDrawableBuilder setIcon(IconValue iconValue){
        icon = iconValue;
        return this;
    }

    /**
     * Set the size of this icon to the standard Android ActionBar.
     * @return The current IconDrawable for chaining.
     */
    public MaterialDrawableBuilder setToActionbarSize() {
        return setSizeDp(ANDROID_ACTIONBAR_ICON_SIZE_DP);
    }

    /**
     * Set the size of the drawable.
     * @param dimenRes The dimension resource.
     * @return The current IconDrawable for chaining.
     */
    public MaterialDrawableBuilder setSizeResource(int dimenRes) {
        return setSizePx(context.getResources().getDimensionPixelSize(dimenRes));
    }

    /**
     * Set the size of the drawable.
     * @param size The size in density-independent pixels (dp).
     * @return The current IconDrawable for chaining.
     */
    public MaterialDrawableBuilder setSizeDp(int size) {
        return setSizePx(MaterialIconUtils.convertDpToPx(context, size));
    }

    /**
     * Set the size of the drawable.
     * @param size The size in pixels (px).
     * @return The current IconDrawable for chaining.
     */
    public MaterialDrawableBuilder setSizePx(int size) {
        this.size = size;
        bounds.set(0, 0, size, size);
        return this;
    }

    /**
     * Set the color of the drawable.
     * @param color The color, usually from android.graphics.Color or 0xFF012345.
     * @return The current IconDrawable for chaining.
     */
    public MaterialDrawableBuilder setColor(int color) {
        paint.setColor(color);
        setAlpha(Color.alpha(color));
        return this;
    }

    /**
     * Set the color of the drawable.
     * @param colorRes The color resource, from your R file.
     * @return The current IconDrawable for chaining.
     */
    public MaterialDrawableBuilder setColorResource(int colorRes) {
        setColor(context.getResources().getColor(colorRes));
        return this;
    }

    public MaterialDrawableBuilder setAlpha(int alpha) {
        this.alpha = alpha;
        paint.setAlpha(alpha);
        return this;
    }

    public MaterialDrawableBuilder setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
        return this;
    }

    public MaterialDrawableBuilder clearColorFilter() {
        paint.setColorFilter(null);
        return this;
    }

    public int getOpacity() {
        return this.alpha;
    }

    /**
     * Sets paint style.
     * @param style to be applied
     */
    public MaterialDrawableBuilder setStyle(Paint.Style style) {
        paint.setStyle(style);
        return this;
    }

    private class IconNotSetException extends RuntimeException {
        public IconNotSetException() {
            this("No icon provided when building MaterialDrawable.");
        }

        public IconNotSetException(String message)
        {
            super(message);
        }

        public IconNotSetException(Throwable cause)
        {
            super(cause);
        }

        public IconNotSetException(String message, Throwable cause)
        {
            super(message, cause);
        }
    }

    private class MaterialDrawable extends Drawable {
        private IconValue icon;
        private TextPaint paint;
        private int size = -1;
        private int alpha = 255;

        /**
         * Create a MaterialDrawable.
         * @param icon    The icon you want this drawable to display.
         */
        public MaterialDrawable(IconValue icon, TextPaint paint, int size, int alpha) {
            this.icon = icon;
            this.paint = paint;
            this.setSizePx(size);
            this.setAlpha(alpha);

            invalidateSelf();
        }

        /**
         * Set the size of the drawable.
         * @param size The size in pixels (px).
         * @return The current IconDrawable for chaining.
         */
        public MaterialDrawable setSizePx(int size) {
            this.size = size;
            setBounds(0, 0, size, size);
            invalidateSelf();
            return this;
        }

        /*
        public MaterialDrawable setIcon(IconValue iconin){
            this.icon = iconin;
            invalidateSelf();
            return this;
        }

        public MaterialDrawable setTextPaint(TextPaint p){
            this.paint = p;
            invalidateSelf();
            return this;
        }*/

        @Override
        public int getIntrinsicHeight() {
            return size;
        }

        @Override
        public int getIntrinsicWidth() {
            return size;
        }

        private final Rect mCachedRect = new Rect();
        @Override
        public void draw(Canvas canvas) {
            // Center drawable within available bounds
            int boundsWidth = getBounds().width();
            int boundsHeight = getBounds().height();
            int dimen = Math.min(boundsWidth, boundsHeight);

            paint.setTextSize(dimen);
            String textValue = MaterialIconUtils.getIconString(icon.ordinal());
            paint.getTextBounds(textValue, 0, 1, mCachedRect);
            float textBottom = getBounds().top + (boundsHeight - mCachedRect.height()) / 2f + mCachedRect.height() - mCachedRect.bottom;

            canvas.drawText(textValue, getBounds().left + boundsWidth / 2f, textBottom, paint);
        }

        @Override
        public boolean isStateful() {
            return true;
        }

        @Override
        public boolean setState(int[] stateSet) {
            int oldValue = paint.getAlpha();
            int newValue = alpha;//Utils.isEnabled(stateSet) ? alpha : alpha / 2;
            paint.setAlpha(newValue);
            return oldValue != newValue;
        }

        @Override
        public void setAlpha(int alpha) {
            this.alpha = alpha;
            paint.setAlpha(alpha);
        }

        @Override
        public void setColorFilter(ColorFilter cf) {
            paint.setColorFilter(cf);
        }

        @Override
        public void clearColorFilter() {
            paint.setColorFilter(null);
        }

        @Override
        public int getOpacity() {
            return PixelFormat.UNKNOWN;
        }

        /**
         * Sets paint style.
         * @param style to be applied
         */
        public void setStyle(Paint.Style style) {
            paint.setStyle(style);
        }

    }
}