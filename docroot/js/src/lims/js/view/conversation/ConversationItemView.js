/**
 * Group View Item
 *
 * The class extends Y.View. It represents the view for a single group item.
 */
Y.namespace('LIMS.View');

Y.LIMS.View.ConversationItemView = Y.Base.create('conversationViewItem', Y.View, [], {

    // This customizes the HTML used for this view's container node.
    containerTemplate: '<li class="conversation-item"/>',

    // Specify an optional model to associate with the view.
    model: Y.LIMS.Model.MessageItemModel,

    // The template property holds the contents of the #lims-group-item-template
    // element, which will be used as the HTML template for each group item.
    template: Y.one('#lims-conversation-item-template').get('innerHTML'),

    /**
     * Renders view
     *
     * @returns {ConversationItemView}
     */
    render: function () {
        // Vars
        var container = this.get('container'),      // Container that holds the view
            model = this.get('model'),              // Message model
            from = model.get('from');               // Creator of the message

        // Fill data from model to template and set it to container
        container.set('innerHTML', Y.Lang.sub(this.template, {
                createdPrettified: "a minute ago",  // TODO: Implement prettify
                fullName: from.get('fullName'),
                content: model.get('body'),
                portrait: this._renderPortrait(from.get('screenName'))
            })
        );

        return this;
    },

    /**
     * Renders portrait based on screenName and returns the rendered HTML
     *
     * @param screenName of the user whose portrait should be rendered
     * @returns HTML of the rendered portrait
     * @private
     */
    _renderPortrait: function (screenName) {
        // Vars
        var portraitView = new Y.LIMS.View.PortraitView({screenName: screenName});
        // Render
        portraitView.render();

        return portraitView.get('container').get('outerHTML');
    }

}, {

    // Specify attributes and static properties for your View here.
    ATTRS: {
        // Override the default container attribute.
        container: {
            valueFn: function () {
                return Y.Node.create(this.containerTemplate);
            }
        },
        // Instance of model attached to view
        model: {
            value: null // Y.LIMS.Model.MessageItemModel
        }
    }

});

