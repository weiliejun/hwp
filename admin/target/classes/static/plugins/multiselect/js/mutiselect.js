/************************************************************************************************************

 (C) www.dhtmlgoodies.com, October 2005
 This is a script from www.dhtmlgoodies.com. You will find this and a lot of other scripts at our website.
 Terms of use:
 You are free to use this script as long as the copyright message is kept intact. However, you may not
 redistribute, sell or repost it without our permission.
 Thank you!
 www.dhtmlgoodies.com
 Alf Magne Kalleland

 exampe
 <form method="post" action="multiple_select.html"><select multiple
 name="fromBox" id="fromBox">
 <option value="3">Finland</option>
 <option value="4">France</option>
 <option value="6">Mexico</option>
 <option value="1">Norway</option>
 <option value="5">Spain</option>
 <option value="2">United Kingdom</option>
 </select> <select multiple name="toBox" id="toBox">
 <option value="12">Canada</option>
 <option value="13">Germany</option>
 <option value="11">United States</option>
 </select></form>
 <script type="text/javascript">
 createMovableOptions("fromBox","toBox",500,300,'Available countries','Selected countries');
 </script>
 ************************************************************************************************************/

var fromBoxArray = new Array();
var toBoxArray = new Array();
var selectBoxIndex = 0;

function moveSingleElement() {
    var selectBoxIndex = this.parentNode.parentNode.id.replace(/[^\d]/g, '');
    var tmpFromBox;
    var tmpToBox;
    if (this.tagName.toLowerCase() == 'select') {
        tmpFromBox = this;
        if (tmpFromBox == fromBoxArray[selectBoxIndex]) tmpToBox = toBoxArray[selectBoxIndex]; else tmpToBox = fromBoxArray[selectBoxIndex];
    } else {

        if (this.value.indexOf('>') >= 0) {
            tmpFromBox = fromBoxArray[selectBoxIndex];
            tmpToBox = toBoxArray[selectBoxIndex];
        } else {
            tmpFromBox = toBoxArray[selectBoxIndex];
            tmpToBox = fromBoxArray[selectBoxIndex];
        }
    }

    for (var no = 0; no < tmpFromBox.options.length; no++) {
        if (tmpFromBox.options[no].selected) {
            tmpFromBox.options[no].selected = false;
            tmpToBox.options[tmpToBox.options.length] = new Option(tmpFromBox.options[no].text, tmpFromBox.options[no].value);

            for (var no2 = no; no2 < (tmpFromBox.options.length - 1); no2++) {
                tmpFromBox.options[no2].value = tmpFromBox.options[no2 + 1].value;
                tmpFromBox.options[no2].text = tmpFromBox.options[no2 + 1].text;
                tmpFromBox.options[no2].selected = tmpFromBox.options[no2 + 1].selected;
            }
            no = no - 1;
            tmpFromBox.options.length = tmpFromBox.options.length - 1;

        }
    }


    var tmpTextArray = new Array();
    for (var no = 0; no < tmpFromBox.options.length; no++) {
        tmpTextArray.push(tmpFromBox.options[no].text + '___' + tmpFromBox.options[no].value);
    }
    tmpTextArray.sort();
    var tmpTextArray2 = new Array();
    for (var no = 0; no < tmpToBox.options.length; no++) {
        tmpTextArray2.push(tmpToBox.options[no].text + '___' + tmpToBox.options[no].value);
    }
    tmpTextArray2.sort();

    for (var no = 0; no < tmpTextArray.length; no++) {
        var items = tmpTextArray[no].split('___');
        tmpFromBox.options[no] = new Option(items[0], items[1]);

    }

    for (var no = 0; no < tmpTextArray2.length; no++) {
        var items = tmpTextArray2[no].split('___');
        tmpToBox.options[no] = new Option(items[0], items[1]);
    }
}

function moveAllElements() {
    var selectBoxIndex = this.parentNode.parentNode.id.replace(/[^\d]/g, '');
    var tmpFromBox;
    var tmpToBox;
    if (this.value.indexOf('>') >= 0) {
        tmpFromBox = fromBoxArray[selectBoxIndex];
        tmpToBox = toBoxArray[selectBoxIndex];
    } else {
        tmpFromBox = toBoxArray[selectBoxIndex];
        tmpToBox = fromBoxArray[selectBoxIndex];
    }

    for (var no = 0; no < tmpFromBox.options.length; no++) {
        tmpToBox.options[tmpToBox.options.length] = new Option(tmpFromBox.options[no].text, tmpFromBox.options[no].value);
    }

    tmpFromBox.options.length = 0;

}


function createMovableOptions(fromBox, toBox, totalWidth, totalHeight, labelLeft, labelRight) {
    fromObj = document.getElementById(fromBox);
    toObj = document.getElementById(toBox);


    fromObj.ondblclick = moveSingleElement;
    toObj.ondblclick = moveSingleElement;


    fromBoxArray.push(fromObj);
    toBoxArray.push(toObj);

    var parentEl = fromObj.parentNode;

    var parentDiv = document.createElement('DIV');
    parentDiv.className = 'multipleSelectBoxControl';
    parentDiv.id = 'selectBoxGroup' + selectBoxIndex;
    parentDiv.style.width = totalWidth + 'px';
    parentDiv.style.height = totalHeight + 'px';
    parentEl.insertBefore(parentDiv, fromObj);


    var subDiv = document.createElement('DIV');
    subDiv.style.width = (Math.floor(totalWidth / 2) - 15) + 'px';
    fromObj.style.width = (Math.floor(totalWidth / 2) - 15) + 'px';

    var label = document.createElement('SPAN');
    label.innerHTML = labelLeft;
    subDiv.appendChild(label);

    subDiv.appendChild(fromObj);
    subDiv.className = 'multipleSelectBoxDiv';
    parentDiv.appendChild(subDiv);


    var buttonDiv = document.createElement('DIV');
    buttonDiv.style.verticalAlign = 'middle';
    buttonDiv.style.paddingTop = (totalHeight / 2) - 50 + 'px';
    buttonDiv.style.paddingLeft = 2 + 'px';
    buttonDiv.style.width = '30px';
    buttonDiv.style.textAlign = 'center';
    buttonDiv.style.fontSize = '12px';
    parentDiv.appendChild(buttonDiv);

    var buttonRight = document.createElement('INPUT');
    buttonRight.type = 'button';
    buttonRight.value = '>';
    buttonDiv.appendChild(buttonRight);
    buttonRight.onclick = moveSingleElement;

    var buttonAllRight = document.createElement('INPUT');
    buttonAllRight.type = 'button';
    buttonAllRight.value = '>>';
    buttonAllRight.onclick = moveAllElements;
    buttonDiv.appendChild(buttonAllRight);

    var buttonLeft = document.createElement('INPUT');
    buttonLeft.style.marginTop = '10px';
    buttonLeft.type = 'button';
    buttonLeft.value = '<';
    buttonLeft.onclick = moveSingleElement;
    buttonDiv.appendChild(buttonLeft);

    var buttonAllLeft = document.createElement('INPUT');
    buttonAllLeft.type = 'button';
    buttonAllLeft.value = '<<';
    buttonAllLeft.onclick = moveAllElements;
    buttonDiv.appendChild(buttonAllLeft);

    var subDiv = document.createElement('DIV');
    subDiv.style.width = (Math.floor(totalWidth / 2) - 15) + 'px';
    toObj.style.width = (Math.floor(totalWidth / 2) - 15) + 'px';

    var label = document.createElement('SPAN');
    label.innerHTML = labelRight;
    subDiv.appendChild(label);

    subDiv.appendChild(toObj);
    parentDiv.appendChild(subDiv);

    toObj.style.height = (totalHeight - label.offsetHeight) + 'px';
    fromObj.style.height = (totalHeight - label.offsetHeight) + 'px';


    selectBoxIndex++;

}

function selectItem() {
    var obj = document.getElementById('toBox[]');
    for (var no = 0; no < obj.options.length; no++) {
        obj.options[no].selected = true;
    }
}