import PropTypes from "prop-types";
import React from "react";

MyComponent.PropTypes = {
    optionalArray: PropTypes.array,
    optionalBool: PropTypes.bool,
    optionalFunc: PropTypes.func,
    optionalNumber: PropTypes.number,
    optionalObject: PropTypes.object,
    optionalString: PropTypes.string,
    optionalSymbol: PropTypes.symbol,
   
    // Bất cứ thứ gì có thể render như : numbers, strings, elements hoặc array
    optionalNode: PropTypes.node,
   
    //  React Element
    optionalElement: PropTypes.element,
   
    // React Element Type
    optionalElementType: PropTypes.elementType,
   
    // Instanace
    optionalMessage: PropTypes.instanceOf(Message),
   
    // Gía trị của props bao gồm
    optionalEnum: PropTypes.oneOf(["News", "Photos"]),
   
    // Một object có thể bao gồm nhiều kiểu
    optionalUnion: PropTypes.oneOfType([
      PropTypes.string,
      PropTypes.number,
      PropTypes.instanceOf(Message)
    ]),
   
    // Một mảng chứa giá trị là các kiểu
    optionalArrayOf: PropTypes.arrayOf(PropTypes.number),
   
    // Một object có thuộc tính là kiểu
    optionalObjectOf: PropTypes.objectOf(PropTypes.number),
   
    // Một object mà mỗi phần có kiểu dữ liệu riêng
    optionalObjectWithShape: PropTypes.shape({
      color: PropTypes.string,
      fontSize: PropTypes.number
    }),
   
    // Một object có cảnh báo về các thuộc tính bổ sung
    optionalObjectWithStrictShape: PropTypes.exact({
      name: PropTypes.string,
      quantity: PropTypes.number
    }),
   
    // Bạn có thể xâu chuỗi bất kỳ câu hỏi nào ở trên với `isRequired` để đảm bảo cảnh báo
    // được hiển thị nếu prop không được cung cấp.
    requiredFunc: PropTypes.func.isRequired,
   
    // Bất kì kiểu dữ liệu nào nhưng nó phải tồn tại..
    requiredAny: PropTypes.any.isRequired,
   
    //Chỉ định props validation tùy chỉnh
    customProp: function(props, propName, componentName) {
      if (!/matchme/.test(props[propName])) {
        return new Error(
          "Invalid prop `" +
            propName +
            "` supplied to" +
            " `" +
            componentName +
            "`. Validation failed."
        );
      }
    },
   
    // Bạn cũng có thể cung cấp trình xác nhận tùy chỉnh cho `ArrayOf` và` objectOf`.
    // Nó sẽ trả về một đối tượng Error nếu xác nhận thất bại. Trình xác nhận
    // sẽ được gọi cho mỗi khóa trong mảng hoặc đối tượng. Hai cái đầu tiên
    // đối số của trình xác nhận là chính mảng hoặc đối tượng và
    // khóa hiện tại của mục.
    customArrayProp: PropTypes.arrayOf(function(
      propValue,
      key,
      componentName,
      location,
      propFullName
    ) {
      if (!/matchme/.test(propValue[key])) {
        return new Error(
          "Invalid prop `" +
            propFullName +
            "` supplied to" +
            " `" +
            componentName +
            "`. Validation failed."
        );
      }
    })
  };