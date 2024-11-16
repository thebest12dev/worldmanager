


///
/// The main package used by worldmanager for virtually everything.
/// It includes built-in utilities and classes to be used by
/// any project, specifically worldmanager.
///
/// ##### Status codes
/// Status codes are a fixed-length DWORD integer representing
/// the status codes when worldmanager crashes/stops.
///
/// Status codes may return successful outcomes, neutral or
/// failed outcomes depending on the type of code. For example,
/// here are some types of codes:
///
/// Status codes that may be successful always start to the
/// very right and must be either `0x00000000`(generic success)
/// or `0x00000003` (success, only given when worldmanager
/// successfully closes because of an action).
///
/// Status codes that may represent an action/or neutral outcomes
/// typically always start to the very right and usually end with
/// a hexadecimal `f` signifying an action, except for `0x00000002`
/// (update completion) like `0x0000003f` (pass to another program)
///  or `0x0000001f` (waiting until action).
///
/// Status codes that may be failed always start with this syntax
///  where the 2 leftmost digits are the exception type following
/// by a hexadecimal `f2` then by 3 digits of the status code
/// following by a hexadecimal `f`. Examples include `0x13f20001`
/// (UpdateBuildException) and `0x00000001` (generic fail).
///
///
package com.thebest12lines.worldmanager;